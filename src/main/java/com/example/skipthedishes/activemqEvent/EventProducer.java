package com.example.skipthedishes.activemqEvent;

import com.example.skipthedishes.entity.Adjustment;
import com.example.skipthedishes.entity.Bonus;
import com.example.skipthedishes.entity.Delivery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventProducer {


    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${active-mq.topic}")
    private String delivery_topic;

    @Value("${active-mq.adjust-topic}")
    private String adjustment_topic;

    @Value("${active-mq.bonus-topic}")
    private String bonus_topic;

    public void sendDeliveryCreationMessage(Delivery delivery){
        try{
            log.info("Attempting Send message to Topic: "+ delivery_topic);
            jmsTemplate.convertAndSend(delivery_topic, delivery);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }

    public void sendBonusModificationMessage(Bonus bonus){
        try{
            log.info("Attempting Send message to Topic: "+ bonus_topic);
            jmsTemplate.convertAndSend(bonus_topic, bonus);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }

    public void sendAdjustmentModificationMessage(Adjustment adjustment){
        try{
            log.info("Attempting Send message to Topic: "+ adjustment_topic);
            jmsTemplate.convertAndSend(adjustment_topic, adjustment);
        } catch(Exception e){
            log.error("Recieved Exception during send Message: ", e);
        }
    }

}
