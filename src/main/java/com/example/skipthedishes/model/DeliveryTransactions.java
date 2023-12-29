package com.example.skipthedishes.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeliveryTransactions {

    private BigDecimal transactionSum;

    private Integer deliveryId;

    public DeliveryTransactions(Integer deliveryId, BigDecimal transactionSum){
        this.transactionSum = transactionSum;
        this.deliveryId = deliveryId;
    }


}
