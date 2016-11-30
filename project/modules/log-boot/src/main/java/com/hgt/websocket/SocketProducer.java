package com.hgt.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * socket消息的真实生产者
 */
@Component
public class SocketProducer {

    private static final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    @Autowired
    private SimpMessagingTemplate template;

    public void sendMessageTo(String topic, String message) {

        StringBuilder builder = new StringBuilder();
//        builder.append("[");
//        builder.append(dateFormatter.format(new Date()));
//        builder.append("] ");
        builder.append(message);

        this.template.convertAndSend("/topic/" + topic, builder.toString());
    }
}
