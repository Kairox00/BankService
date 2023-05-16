package com.indigo.bankservice.broker;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class Publisher {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public Publisher(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(String queueName, String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

    public void publish(String queueName, HashMap<String, String> message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }
}
