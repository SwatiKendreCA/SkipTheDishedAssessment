package com.example.skipthedishes.activemqEvent;

import com.example.skipthedishes.entity.Delivery;
import com.example.skipthedishes.repository.DeliveryRepository;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeliveryEventListener implements MessageListener {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Override
    @JmsListener(destination = "${active-mq.topic}")
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            Delivery delivery = (Delivery) objectMessage.getObject();
            //do additional processing
            log.info("Received Message: "+ delivery.toString());
            deliveryRepository.save(delivery);
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }

    }

}
