package com.example.skipthedishes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "adjustment")
@Data
public class Adjustment {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "adjustment_id")
    private Integer adjustmentId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", referencedColumnName = "deliveryId")
    private Delivery delivery;

    @Column(name = "modified_timestamp")
    private Timestamp modifiedTimestamp;

    @Column(name = "value")
    private Double value;


}
