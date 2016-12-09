package com.hgt.logger.heartbeat;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * 心跳发送socket端
 */
public class ClientSender {

    public ClientSender() {
    }

    Socket sender = null;
    private static ClientSender instance;

    // 获得ClientSender实例
    public static ClientSender getInstance() {

        synchronized (HeartBeatClient.class) {
            if (null == instance) {
                instance = new ClientSender();
            }
        }
        return instance;
    }

    // 给心跳包赋值，并且发送到输出流中
    public void send(HashMap<String, String> configMap) {

        try {
            // Creates a stream socket and connects it   at the specified IP address & port.
            sender = new Socket(InetAddress.getByName(configMap.get("host")), 18701);
            while (true) {
                ObjectOutputStream out = new ObjectOutputStream(sender.getOutputStream());
                String dateNow = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                //发送心跳的json格式不能修改，可直接入库，appCode通过使用日志工具的log4j.properties指定，beatMsg
                String beatJson = "{" +
                        "\"beatId\":\"" + configMap.get("appid") + dateNow.replace("-", "").replace(" ", "").replace(":", "") + "\"," +
                        "\"appCode\":\"" + configMap.get("appid") + "\"," +
                        "\"beatTime\":\"" + dateNow + "\"," +
                        "\"beatMsg\":\"" + configMap.get("msg") + "\"" +
                        "}";
                out.writeObject(beatJson);
                out.flush();
//                System.out.println("心跳已发送: " + beatJson);
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}