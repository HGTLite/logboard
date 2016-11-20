package com.hgt;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class App {

    public static void main(String[] args) {

        JedisPool pool = new JedisPool(new JedisPoolConfig(), "123.206.29.126", 6379);
        // JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.99.75",6379);
        // Jedis implements Closable. Hence, the jedis instance will be auto-closed after the last statement.
        try (Jedis jedis = pool.getResource()) {
            // / ... do stuff here ... for example
            jedis.set("foo2", "bar2");
            String foobar = jedis.get("foo1");
            jedis.zadd("sose", 0, "car");
            jedis.zadd("sose", 0, "bike");
            Set<String> sose = jedis.zrange("sose", 0, -1);
            System.out.println(foobar);
            System.out.println(sose);
        }
        // / ... when closing your application:
        pool.close();
    }
}