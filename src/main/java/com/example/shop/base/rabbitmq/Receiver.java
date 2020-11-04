package com.example.shop.base.rabbitmq;

import org.apache.catalina.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 *消费消息
 */
@Component
@RabbitListener(queues = "consumer_queue")
public class Receiver {

    @RabbitHandler
    public void processMessage1(@Payload User user) {
        System.out.println(user.getName());
    }

}