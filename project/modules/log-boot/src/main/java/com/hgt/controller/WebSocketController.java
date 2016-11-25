package com.hgt.controller;

import com.hgt.websocket.SocketProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {



    @Autowired
    SocketProducer producer;

    @RequestMapping("/send/{topic}")
    public String sender(@PathVariable String topic, @RequestParam String message) {
        producer.sendMessageTo(topic, message);
        return "消息已经发送到 "+topic+" 主题" ;
    }

}
