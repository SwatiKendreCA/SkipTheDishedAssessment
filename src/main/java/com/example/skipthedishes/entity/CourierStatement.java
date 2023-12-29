package com.example.skipthedishes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "courier_statement")
@Data
public class CourierStatement {

    private Integer courierId;

    private BigDecimal totalTransactionAmount;

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;


}
