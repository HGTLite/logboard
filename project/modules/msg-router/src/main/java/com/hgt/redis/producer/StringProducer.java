package com.hgt.redis.producer;

import com.hgt.bean.ContentBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * README:
 * Created by Yao on 11/25/16.
 * =============================================================================
 * CHANGELOG:
 */
@Component
public class StringProducer implements ProducerI {

    private static final Logger log = LoggerFactory.getLogger(StringProducer.class);
    private StringRedisTemplate template;

    @Autowired
    public StringProducer(StringRedisTemplate template){
        this.template = template;
    }

    @Override
    public ContentBean sendTo(String topic, String msg){
        log.info("即将发送到 "+topic+" 主题");
        this.template.convertAndSend(topic, msg);
        return new ContentBean("消息成功发送到 "+topic+" 主题");
    }

}
