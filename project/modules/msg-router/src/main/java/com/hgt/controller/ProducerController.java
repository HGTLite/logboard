package com.hgt.controller;

import com.hgt.bean.ContentBean;
import com.hgt.redis.producer.StringProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * README:
 * Created by Yao on 11/25/16.
 * =============================================================================
 * CHANGELOG:
 */
@RestController
public class ProducerController {

    public final String BASE_URL = "msg";

    @Autowired
    StringProducer producer;

    @RequestMapping(value = BASE_URL + "/{topicName}/{msg}", method = RequestMethod.GET)
    public ContentBean sendStr2All(@PathVariable String topicName, @PathVariable String msg) {

        System.out.println("即将发送消息：" + msg);

        producer.sendTo(topicName, msg);

        return new ContentBean("发送成功： " + msg);

    }

    @RequestMapping(value = BASE_URL + "/test", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }


}
