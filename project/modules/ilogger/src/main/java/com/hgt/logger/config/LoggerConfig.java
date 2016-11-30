package com.hgt.logger.config;

import com.hgt.logger.heartbeat.HeartBeatClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * README:读取配置文件进行初始化
 * Created by Yao on 11/29/16.
 * =============================================================================
 * CHANGELOG:
 */
public class LoggerConfig {

    private static LoggerConfig instance;

    // 获得LoggerConfig实例
    public static LoggerConfig getInstance() {
        synchronized (HeartBeatClient.class) {
            if (null == instance) {
                instance = new LoggerConfig();
            }
        }
        return instance;
    }

    /**
     * 读取目标心跳服务器和客户机appid
     *
     * @return
     */
    public HashMap<String, String> setHeartBeatConfig() {

        HashMap<String, String> configMap = new HashMap<>();

        InputStream inputStream = LoggerConfig.class.getClassLoader().getResourceAsStream("log4j.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String beatingHost = p.getProperty("beating.host");
        String appid = p.getProperty("beating.appid");

        //TODO：统一设置日志级别
        String logLevel = p.getProperty("log.level");
        if (logLevel != null) {
            configMap.put("loglevel", logLevel);
        } else {
            configMap.put("loglevel", "DEBUG");
        }

        configMap.put("host", beatingHost);
        configMap.put("appid", appid);
        //System.out.println("=====beating.host " + beatingHost);
        return configMap;
    }

}
