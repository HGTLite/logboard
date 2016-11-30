package com.hgt.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * README:
 * Created by Yao on 11/30/16.
 * =============================================================================
 * CHANGELOG:
 */
public class PropertiesUtils {

    public static void main(String[] args) {

        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("config.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //获取key对应的value值
        String aname2 = properties.getProperty("app.name2");
        System.out.println(aname2);
        String fname = properties.getProperty("fname");
        System.out.println(fname);
    }

}
