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
    public void consume(HashMap<String, Object> message) {
        HashMap<String, Object> response = new HashMap<>(message);
        try{
            Double amount = (Double) message.get("amount");
            String command = (String) message.get("command");
            switch (command){
                case "INCREASE_BALANCE":
                    bankService.increaseBalance((String) message.get("ccn"), amount);
                    break;

                case "DECREASE_BALANCE":
                    bankService.deductFromBalance((String) message.get("ccn"),amount);
                    response.put("status", "SUCCESS");
                    publisher.publish(mqConstants.bank_res, response);
                    break;

                default:
                    throw new Exception("Invalid command for Bank Service: "+ command);
            }
        }
        catch (Exception e){
             response.put("status", e.getMessage());
             publisher.publish(mqConstants.bank_res, response);
        }


    }

}
