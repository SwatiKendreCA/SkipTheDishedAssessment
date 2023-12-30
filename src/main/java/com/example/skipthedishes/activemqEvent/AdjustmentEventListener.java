package com.example.skipthedishes.activemqEvent;

import com.example.skipthedishes.entity.Adjustment;
import com.example.skipthedishes.repository.AdjustmentRepository;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdjustmentEventListener implements MessageListener {

    @Autowired
    AdjustmentRepository adjustmentRepository;

    @Override
    @JmsListener(destination = "${active-mq.adjust-topic}")
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            Adjustment adjustment = (Adjustment) objectMessage.getObject();
            //do additional processing
            log.info("Received Message: "+ adjustment.toString());
            adjustmentRepository.save(adjustment);
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }

    }
}
