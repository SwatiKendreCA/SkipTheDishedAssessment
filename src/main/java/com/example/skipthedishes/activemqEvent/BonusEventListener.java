package com.example.skipthedishes.activemqEvent;

import com.example.skipthedishes.entity.Bonus;
import com.example.skipthedishes.repository.BonusRepository;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BonusEventListener implements MessageListener {

    @Autowired
    BonusRepository bonusRepository;

    @Override
    @JmsListener(destination = "${active-mq.bonus-topic}")
    public void onMessage(Message message) {
        try{
            ObjectMessage objectMessage = (ObjectMessage)message;
            Bonus bonus = (Bonus) objectMessage.getObject();
            //do additional processing
            log.info("Received Message: "+ bonus.toString());
            bonusRepository.save(bonus);
        } catch(Exception e) {
            log.error("Received Exception : "+ e);
        }

    }
}
