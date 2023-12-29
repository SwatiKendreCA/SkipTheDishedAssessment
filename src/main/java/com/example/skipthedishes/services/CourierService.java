package com.example.skipthedishes.services;

import com.example.skipthedishes.model.DeliveryTransactions;
import com.example.skipthedishes.model.WeeklyCourierStatement;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

public interface CourierService {
    List<DeliveryTransactions> getDeliveryTransactions(Integer courierId, Date startDate, Date endDate);

    WeeklyCourierStatement getWeeklyCourierStatement(Integer courierId);
}
