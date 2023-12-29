package com.example.skipthedishes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "courier")
@Data
public class Courier {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "courier_id")
    private Integer courierId;

    @Column(name = "courier_created_time")
    private Timestamp courierCreatedTime;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "customer_mob_no")
    private String customerMobNo;

    @Column(name = "customer_email_id")
    private String customerEmailId;



}
