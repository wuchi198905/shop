package com.example.shop.base.rabbitmq;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *消息序列化
 * 涉及网络传输的应用序列化不可避免，发送端以某种规则将消息转成 byte 数组进行发送，接收端则以约定的规则进行 byte[] 数组的解析，RabbitMQ 的序列化是指 Message 的 body 属性，即我们真正需要传输的内容，RabbitMQ 抽象出一个 MessageConvert 接口处理消息的序列化，其实现有 SimpleMessageConverter（默认）、Jackson2JsonMessageConverter。
 *
 * 　　SimpleMessageConverter 对于要发送的消息体 body 为 byte[] 时不进行处理，如果是 String 则转成字节数组,如果是 Java 对象，则使用 jdk 序列化将消息转成字节数组，转出来的结果较大，含class类名，类相应方法等信息。因此性能较差；当使用 RabbitMQ 作为中间件时，数据量比较大，此时就要考虑使用类似 Jackson2JsonMessageConverter 等序列化形式以此提高性能。
 *
 * 　　Jackson2JsonMessageConverter配置如下：
 *
 * 　　1、消息发送者设置序列化方式 ：rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
 *
 * 　　2、消息消费者也应配置 MessageConverter 为 Jackson2JsonMessageConverter
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

}
