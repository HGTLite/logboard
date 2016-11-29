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
    public void send(HashMap<String,String> configMap) {
        try {
            // Creates a stream socket and connects it   at the specified IP address & port.
            sender = new Socket(InetAddress.getByName(configMap.get("host")), 18701);
            while (true) {
                ObjectOutputStream out = new ObjectOutputStream(sender.getOutputStream());
                String dateNow = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
                String dateJson = "{" +
                        "\"appid\":\"" + configMap.get("appid") + "\"," +
                        "\"beats\":\"  " + dateNow + "\"" +
                        "}";
                out.writeObject(dateJson);
                out.flush();
                //System.out.println("已发送: " + dateJson);
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}