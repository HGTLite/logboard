package com.hgt.controller;

import com.hgt.bean.ContentBean;
import com.hgt.redis.producer.StringProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    StringProducer stringProducer;


    @RequestMapping(value = BASE_URL + "/{topicName}", method = RequestMethod.POST)
    public ContentBean sendStr2All(@PathVariable String topicName, @RequestParam String msgTag, @RequestParam String msgBody) {

        if (msgTag == null || msgTag.trim().isEmpty()) {
            return new ContentBean("参数内容不能为空");
        }

        System.out.println("即将发送消息 参数msgTag：" + msgTag + "  ;  参数msgBody是" + msgBody);

        String msgJson = "{" +
                "\"msgTag\":\"" + msgTag + "\"," +
                "\"msgBody\":\"" + msgBody + "\"" +
                "}";

        stringProducer.sendTo(topicName, msgJson);

        return new ContentBean("发送成功： " + msgJson);

    }

    @RequestMapping(value = BASE_URL + "/test", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }


}
