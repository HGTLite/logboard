package com.hgt.redis.producer;

import com.hgt.bean.ContentBean;

/**
 * README:
 * Created by Yao on 11/25/16.
 * =============================================================================
 * CHANGELOG:
 */
public interface ProducerI {

    public ContentBean sendTo(String topic, String msg);

}
