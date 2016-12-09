package com.hgt.logger.config;

import com.hgt.logger.heartbeat.HeartBeatClient;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
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
            System.out.println("读取配置文件出现异常，程序结束");
            e.printStackTrace();
            System.exit(0);
        }

        String beatingHost = p.getProperty("beating.host");
        String appid = p.getProperty("beating.appid");
        String msg = p.getProperty("beating.msg");

        if (beatingHost == null || beatingHost.length() == 0) {
            System.out.println("配置文件未配置心跳接收服务器ip，程序结束");
            System.exit(0);
        }

        if (appid == null || appid.length() == 0) {
            appid = new SimpleDateFormat("yyyyMMddHHmmss") + "-unknown-app";
        }

        if (msg == null || msg.length() == 0) {
            msg = "default-group";
        }

        configMap.put("host", beatingHost);
        configMap.put("appid", appid);
        configMap.put("msg", msg);

        //TODO：统一设置日志级别
        String logLevel = p.getProperty("log.level");
        if (logLevel == null || logLevel.length() == 0) {
            configMap.put("loglevel", "DEBUG");
        } else {
            configMap.put("loglevel", logLevel);
        }

        //System.out.println("=====beating.host " + beatingHost);

        return configMap;
    }

}
