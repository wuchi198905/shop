package com.example.shop.base.rabbitmq;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.Channel;

/**
 * 消费者
 */
@Component
public class MessageConsumer {
    @RabbitListener(queues = QueueConstants.MESSAGE_QUEUE_NAME)
    public void processMessage(Channel channel,Message  message) {
        System.out.println("MessageConsumer收到消息："+new String(message.getBody()));
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {

        }
    }
}
