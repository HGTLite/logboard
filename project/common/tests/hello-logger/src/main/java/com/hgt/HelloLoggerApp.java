package com.hgt;

import com.hgt.logger.StandardLogger;
import com.hgt.logger.formats.LogLevels;
import com.hgt.logger.formats.LogOptions;
import com.hgt.logger.formats.LogType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.hgt.ip.IPHelper.getRandomIp;

@SpringBootApplication
public class HelloLoggerApp {

    public static void main(String[] args) {

        SpringApplication.run(HelloLoggerApp.class, args);
        System.out.println("===HelloLoggerApp 启动完成===");

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

            //日志条数
            int ii = 0;

            @Override
            public void run() {
                StandardLogger logger = new StandardLogger(HelloLoggerApp.class, "hello001");
                logger.logType(LogType.LOGIN).logLevel(LogLevels.DEBUG);
                if (ii % 5 == 0) {
                    logger.options(LogOptions.USER_ID, "user001").options(LogOptions.USER_IP, getRandomIp());
                    logger.w("delay 5 seconds 首次延迟5s执行 and excute every 4 seconds 每次间隔4s执行");
                    ii++;
                } else if (ii % 7 == 0) {
                    logger.options(LogOptions.USER_ID, "user002").options(LogOptions.USER_IP, getRandomIp());
                    logger.e("这是第 " + ii + " 条消息！！！, 7的倍数", new Exception());
                    ii++;
                } else {
                    logger.options(LogOptions.USER_ID, "user003").options(LogOptions.USER_IP, getRandomIp());
                    logger.i("这是第 " + ii + " 条消息！！！");
                    ii++;
                }
            }
        }, 5, 4, TimeUnit.SECONDS);

    }

}