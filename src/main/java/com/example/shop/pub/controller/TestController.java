package com.example.shop.pub.controller;



import com.example.shop.base.rabbitmq.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class TestController {

    @Autowired
    private MessageProducer messageProducer;
//    private WebSocket websocket = new WebSocket();

    @RequestMapping(value = "/index")
    public String index(String str) {
        // 将实体实例写入消息队列
        Map<String,Object>map= new HashMap<>();
        map.put("str",str);
        messageProducer.sendMessage(map.toString());
        return "Success";
    }
    /**
     * 服务端推送消息对客户端
     * @ClassName: ServiceClientController
     * @Description: TODO
     * @author OnlyMate
     * @Date 2018年8月16日 下午2:45:22
     *
     */





//        @RequestMapping("/test")
//        public void sendMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
//            JSONObject json = new JSONObject();
//            json.put("to", request.getSession().getId());
//            json.put("msg", "欢迎连接WebSocket！！！！");
//            websocket.onMessage(json.toJSONString());
//
//    }
}
