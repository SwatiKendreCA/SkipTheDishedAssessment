package com.example.skipthedishes.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WeeklyCourierStatement {

    private BigDecimal totalTransactionAmount;

    private Integer courierId;

    public WeeklyCourierStatement(Integer courierId, BigDecimal totalTransactionAmount) {
        this.courierId = courierId;
        this.totalTransactionAmount = totalTransactionAmount;
    }
}
