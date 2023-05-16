package com.indigo.bankservice.broker.consumers;

import com.indigo.bankservice.broker.MQConstants;
import com.indigo.bankservice.broker.Publisher;
import com.indigo.bankservice.services.BankService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Component
public class UpdateBalanceConsumer{
    @Autowired
    BankService bankService;

    @Autowired
    Publisher publisher;

    @Autowired
    MQConstants mqConstants;

    @Transactional
    @RabbitListener(queues = {"#{mqConstants.bank_req}"})
    public void consume(HashMap<String, String> message) {
        HashMap<String, String> response = new HashMap<>(message);
        try{
            Double amount = Double.parseDouble(message.get("amount"));
            if(amount > 0){
                bankService.increaseBalance(message.get("ccn"), amount);
            }
            else {
                bankService.deductFromBalance(message.get("ccn"),-1*amount);
                response.put("status", "SUCCESS");
//                response.put("key", message.get("key"));
                publisher.publish(mqConstants.bank_res, response);
            }

        }
        catch (Exception e){
             response.put("status", e.getMessage());
             publisher.publish(mqConstants.bank_res, response);
        }


    }

}
