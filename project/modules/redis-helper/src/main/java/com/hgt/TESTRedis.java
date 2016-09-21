package com.hgt;

import redis.clients.jedis.Jedis;

/******************************************************************************
 * Created by  Yao  on  2016/9/21
 * README: 
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class TESTRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.99.111",6379);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }
}
