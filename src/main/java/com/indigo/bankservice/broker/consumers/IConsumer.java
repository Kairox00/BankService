package com.indigo.bankservice.broker.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface IConsumer {
    Logger LOGGER = LoggerFactory.getLogger(IConsumer.class);


}
