package com.indigo.bankservice.broker;

import com.indigo.bankservice.broker.MQConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    private final MQConstants mqConstants;
    @Autowired
    public RabbitConfig(MQConstants mqConstants){
         this.mqConstants = mqConstants;
    }
    @Bean
    public Queue bank_req(){
        return new Queue(mqConstants.bank_req);
    }
    @Bean
    public Queue bank_res(){
        return new Queue(mqConstants.bank_res);
    }

//    @Bean
//    public Queue inv_req(){
//        return new Queue(mqConstants.inv_req);
//    }
//    @Bean
//    public Queue inv_res(){
//        return new Queue(mqConstants.inv_res);
//    }


    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
