package com.example.skipthedishes.services;

import com.example.skipthedishes.entity.Adjustment;
import com.example.skipthedishes.entity.Bonus;
import com.example.skipthedishes.entity.CourierStatement;
import com.example.skipthedishes.entity.Delivery;
import com.example.skipthedishes.model.DeliveryTransactions;
import com.example.skipthedishes.model.WeeklyCourierStatement;
import com.example.skipthedishes.repository.AdjustmentRepository;
import com.example.skipthedishes.repository.BonusRepository;
import com.example.skipthedishes.repository.CourierRepository;
import com.example.skipthedishes.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourierServiceImpl implements CourierService {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    AdjustmentRepository adjustmentRepository;

    @Autowired
    BonusRepository bonusRepository;

    @Autowired
    CourierRepository courierRepository;

    @Override
    public List<DeliveryTransactions> getDeliveryTransactions(Integer courierId, Date startDate, Date endDate){
        List<DeliveryTransactions> deliveryTransactions = new ArrayList<>();
        List<Delivery> deliveryList = deliveryRepository.findByCourierIdAndDate(courierId, startDate, endDate);

        List<Adjustment> adjustmentList = null;
        List<Bonus> bonusList=null;
        Map<Integer, BigDecimal> deliveryTransactionMap = new HashMap<>();
        for(Delivery delivery : deliveryList){
            adjustmentList = adjustmentRepository.findByDeliveryId(delivery.getDeliveryId());
            bonusList = bonusRepository.findByDeliveryId(delivery.getDeliveryId());
            deliveryTransactionMap.put(delivery.getDeliveryId(), deliveryTransactionMap.getOrDefault(delivery.getDeliveryId(), BigDecimal.ZERO).add(BigDecimal.valueOf(delivery.getValue())));

        }

        for (Adjustment adjustment : adjustmentList) {
            Integer deliveryId = adjustment.getDelivery().getDeliveryId();
            deliveryTransactionMap.put(deliveryId, deliveryTransactionMap.getOrDefault(deliveryId, BigDecimal.ZERO).add(BigDecimal.valueOf(adjustment.getValue())));
        }

        for (Bonus bonus : bonusList) {
            Integer deliveryId = bonus.getDelivery().getDeliveryId();
            deliveryTransactionMap.put(deliveryId, deliveryTransactionMap.getOrDefault(deliveryId, BigDecimal.ZERO).add(BigDecimal.valueOf(bonus.getValue())));
        }

        // Convert the map to a list of DeliveryTransaction objects
        List<DeliveryTransactions> deliveryTransactionsList = new ArrayList<>();
        for (Map.Entry<Integer, BigDecimal> entry : deliveryTransactionMap.entrySet()) {
            deliveryTransactions.add(new DeliveryTransactions(entry.getKey(), entry.getValue()));
        }

        return deliveryTransactions;
    }

    @Override
    public WeeklyCourierStatement getWeeklyCourierStatement(Integer courierId) {

        List<DeliveryTransactions> deliveryTransactions = getDeliveryTransactions(courierId, null, null);

        // Calculate the total amount for the courier
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (DeliveryTransactions transaction : deliveryTransactions) {
            totalAmount = totalAmount.add(transaction.getTransactionSum());
        }

        // Save or update the weekly courier statement in the database
        CourierStatement courierStatement = courierRepository.findByCourierId(courierId);
        if (courierStatement == null) {
            courierStatement = new CourierStatement();
            courierStatement.setCourierId(courierId);
        }
        courierStatement.setTotalTransactionAmount(totalAmount);
        courierRepository.save(courierStatement);

        // Return the weekly courier statement
        return new WeeklyCourierStatement(courierStatement.getCourierId(), courierStatement.getTotalTransactionAmount());

    }
}
