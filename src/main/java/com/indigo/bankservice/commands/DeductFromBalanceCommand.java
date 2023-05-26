package com.indigo.bankservice.commands;

import com.indigo.bankservice.broker.MQConstants;
import com.indigo.bankservice.broker.Publisher;
import com.indigo.bankservice.services.BankService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope("prototype")
@Setter
public class DeductFromBalanceCommand {

    @Autowired
    BankService bankService;
    @Autowired
    Publisher publisher;
    @Autowired
    MQConstants mqConstants;

    String ccn;
    double amount;
    HashMap<String, Object> response;

    void execute() throws Exception {
        bankService.deductFromBalance(ccn, amount);
        response.put("status", "SUCCESS");
        publisher.publish(mqConstants.bank_res, response);
    }

    void revert() throws Exception{
        bankService.increaseBalance(ccn, amount);
    }
}
