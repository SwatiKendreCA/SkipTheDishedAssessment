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
import jakarta.annotation.security.RunAs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CourierServiceTest {

    @InjectMocks
    private CourierServiceImpl courierService;

    @Mock
    private CourierRepository courierRepository;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private AdjustmentRepository adjustmentRepository;

    @Mock
    private BonusRepository bonusRepository;

    @Test
    public void testGetDeliveryTransactions() {
        // Mock data
        Delivery deliveryCreated = new Delivery();
        deliveryCreated.setDeliveryId(1);
        deliveryCreated.setValue(10.0);
        Adjustment adjustmentModified = new Adjustment();
        adjustmentModified.setDelivery(deliveryCreated);
        adjustmentModified.setValue(10.0);
        Bonus bonusModified = new Bonus();
        bonusModified.setDelivery(deliveryCreated);
        bonusModified.setValue(10.0);

        Mockito.when(deliveryRepository.findByCourierIdAndDate(1,new Date(2017, 1, 30), new Date(2017, 2, 30))) .thenReturn(Collections.singletonList(deliveryCreated));
        Mockito.when(adjustmentRepository.findByDeliveryId(1)).thenReturn(Collections.singletonList(adjustmentModified));
        Mockito.when(bonusRepository.findByDeliveryId(1)).thenReturn(Collections.singletonList(bonusModified));

        // Test the method
        List<DeliveryTransactions> deliveryTransactions = courierService.getDeliveryTransactions(1, new Date(2017, 1, 30), new Date(2017, 2, 30));

        // Verify the result
        Assertions.assertEquals(1, deliveryTransactions.size());
        Assertions.assertEquals(1, deliveryTransactions.get(0).getDeliveryId().longValue());
        Assertions.assertEquals(BigDecimal.valueOf(30.0), deliveryTransactions.get(0).getTransactionSum());
    }

    @Test
    public void testGetWeeklyCourierStatement() {
        // Mock data
        DeliveryTransactions deliveryTransaction = new DeliveryTransactions(1, BigDecimal.TEN);

        CourierStatement courierStatement = new CourierStatement();
        courierStatement.setCourierId(1);
        Delivery delivery = new Delivery();
        delivery.setDeliveryId(1);
        delivery.setValue(10.0);
        List<Adjustment> adjustmentList = new ArrayList<>();
        Adjustment adjustmentModified = new Adjustment();
        adjustmentModified.setDelivery(delivery);
        adjustmentModified.setValue(10.0);
        adjustmentList.add(adjustmentModified);
        List<Bonus> bonusList = new ArrayList<>();
        Bonus bonusModified = new Bonus();
        bonusModified.setDelivery(delivery);
        bonusModified.setValue(10.0);
        bonusList.add(bonusModified);
        Mockito.when(courierRepository.findByCourierId(1)).thenReturn(courierStatement);
        Mockito.when(deliveryRepository.findByCourierIdAndDate(1,null, null)) .thenReturn(Collections.singletonList(delivery));
        Mockito.when(adjustmentRepository.findByDeliveryId(1)).thenReturn(Collections.singletonList(adjustmentModified));
        Mockito.when(bonusRepository.findByDeliveryId(1)).thenReturn(Collections.singletonList(bonusModified));

        // Test the method
        WeeklyCourierStatement weeklyCourierStatement = courierService.getWeeklyCourierStatement(1);

        // Verify the result
        Assertions.assertEquals(1L, weeklyCourierStatement.getCourierId().longValue());
        Assertions.assertEquals(BigDecimal.valueOf(30.0), weeklyCourierStatement.getTotalTransactionAmount());
    }
}
