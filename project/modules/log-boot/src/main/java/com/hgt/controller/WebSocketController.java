package com.hgt.controller;

import com.hgt.websocket.SocketProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 前端消息请求处理类
 */
@RestController
public class WebSocketController {

    @Autowired
    SocketProducer producer;

    @RequestMapping(value = "/send/{topic}",method = RequestMethod.POST)
    public String sender(@PathVariable String topic, @RequestParam String message) {
        producer.sendMessageTo(topic, message);
        return "消息已经发送到 "+topic+" 主题" ;
    }

}
