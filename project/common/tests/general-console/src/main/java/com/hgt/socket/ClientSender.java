package com.hgt.socket;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 客户端发送类
 *
 */
public class ClientSender {

    public ClientSender() {

    }

    Socket sender = null;
    private static ClientSender instance;

    // 获得ClientSender实例
    public static ClientSender getInstance() {
//		if (null == instance) {
//			synchronized(Client.class) {
//				instance = new ClientSender();
//			}
//		}

        synchronized (Client.class) {
            if (null == instance) {
                instance = new ClientSender();
            }
        }
        return instance;
    }

    // 给心跳包赋值，并且发送到输出流中
    public void send() {
        try {
            // Creates a stream socket and connects it to the specified port number at the specified IP address.
            sender = new Socket(InetAddress.getLocalHost(), 11234);
            while (true) {
                ObjectOutputStream out = new ObjectOutputStream(sender.getOutputStream());
                Entity obj = new Entity();
                obj.setName("lisi");
                obj.setSex("man");
                out.writeObject(obj);
                out.flush();
                System.out.println("已发送...");
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}