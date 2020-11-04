package com.example.shop.pub.controller;



import com.example.shop.base.rabbitmq.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController {

    @Autowired
    private MessageProducer messageProducer;

    @RequestMapping(value = "/index")
    public String index(String str) {
        // 将实体实例写入消息队列
        messageProducer.sendMessage(str);
        return "Success";
    }

}
