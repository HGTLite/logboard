package com.hgt.web;

import com.hgt.websocket.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @Autowired
    Producer producer;

    @RequestMapping("/send/{topic}")
    public String sender(@PathVariable String topic, @RequestParam String message) {
        producer.sendMessageTo(topic, message);
        return "OK-Sent " ;
//        return "OK-Sent time is " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

}
