package com.hgt.heartbeat;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端类
 * @author Administrator
 */
public class HeartBeatServer extends Thread {

    //This class implements server sockets
    private ServerSocket server = null;

    Object obj = new Object();

    @Override
    public void run() {

        try {
            server = new ServerSocket(18701);
            while (true) {
                //调用等待监听的方法
                Socket client = server.accept();
                synchronized (obj) {
                    // 客户端线程启动
                    new Thread(new Client(client)).start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 接收socket客户端
     *
     * @author Administrator
     */
    class Client implements Runnable {

        Socket client;

        public Client(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    //getInputStream():返回socket的输入流
                    //ObjectInputStream():Creates an ObjectInputStream that reads from the specified InputStream.
                    ObjectInput in = new ObjectInputStream(client.getInputStream());
                    String gets = (String) in.readObject();
                    // 输出心跳包中的信息
                    System.out.println(gets);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

//    public static void main(String[] args) {
//        System.out.println("开始检测客户端是否在线...");
//        new HeartBeatServer().start();
//    }

}