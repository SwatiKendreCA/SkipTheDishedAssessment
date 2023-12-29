package com.example.skipthedishes.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "bonus")
@Data
public class Bonus {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "bonus_id")
    private Integer bonusId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id", referencedColumnName = "deliveryId")
    private Delivery delivery;

    @Column(name = "modified_timestamp")
    private Timestamp modifiedTimestamp;

    @Column(name = "value")
    private Double value;
}
