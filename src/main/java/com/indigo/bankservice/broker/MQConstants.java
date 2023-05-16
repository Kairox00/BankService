package com.indigo.bankservice.broker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mqConstants")
public class MQConstants {

    @Value("${queue.bank.req.name}")
    public String bank_req;

    @Value("${queue.bank.res.name}")
    public String bank_res;

    @Value("${queue.inventory.req.name}")
    public String inv_req;

    @Value("${queue.inventory.res.name}")
    public String inv_res;

}
