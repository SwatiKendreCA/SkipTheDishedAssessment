package com.example.skipthedishes.controller;

import com.example.skipthedishes.activemqEvent.AdjustmentEventListener;
import com.example.skipthedishes.activemqEvent.EventProducer;
import com.example.skipthedishes.entity.Adjustment;
import com.example.skipthedishes.entity.Bonus;
import com.example.skipthedishes.entity.Delivery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventPublishController {
    @Autowired
    EventProducer eventProducer;

    @PostMapping(value="/api/deliveryCreated")
    public Delivery sendMessage(@RequestBody Delivery delivery){
        eventProducer.sendDeliveryCreationMessage(delivery);
        return delivery;
    }

    @PostMapping(value="/api/deliveryCreated")
    public Adjustment sendAdjustmentMessage(@RequestBody Adjustment adjustment){
        eventProducer.sendAdjustmentModificationMessage(adjustment);
        return adjustment;
    }

    @PostMapping(value="/api/deliveryCreated")
    public Bonus sendBonusMessage(@RequestBody Bonus bonus){
        eventProducer.sendBonusModificationMessage(bonus);
        return bonus;
    }
}
