package com.example.shop.base.rabbitmq;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 */
@Component
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String str) {

        rabbitTemplate.convertAndSend(QueueConstants.MESSAGE_EXCHANGE, QueueConstants.MESSAGE_ROUTE_KEY, str);
    }

}