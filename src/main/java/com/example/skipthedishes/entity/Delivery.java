package com.example.skipthedishes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "delivery")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "delivery_id")
    private Integer deliveryId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "courier_id", referencedColumnName = "courierId")
    private Courier courier;

    @Column(name = "created_timestamp")
    private Timestamp createdTimestamp;

    @Column(name = "value")
    private Double value;

}
