package com.hgt.utils;

import com.hgt.converter.MapJsonConverter;
import com.hgt.msg.HttpUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * README: 异步发送请求
 * Created by Yao on 12/4/16.
 * =============================================================================
 * CHANGELOG:
 */
public class CacheThreadHelper {

    public static String newThreadGetByMap(String targetURL, Map params) {
        final String[] response = new String[1];

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                response[0] = HttpUtil.get(targetURL, params, 2500, 2500, "UTF-8");
            }
        });

        return response[0];
    }

    public static String newThreadPostByMap(String targetURL, Map params) {
        final String[] response = new String[1];

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                response[0] =HttpUtil.post(targetURL, params, 2500, 2500, "UTF-8");
            }
        });
        return response[0];

    }

    public static String newThreadPostByJson(String targetURL, String json) {
        final String[] response = new String[1];

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                response[0] =HttpUtil.postJson(targetURL, json);
            }
        });
        return response[0];

    }


}
