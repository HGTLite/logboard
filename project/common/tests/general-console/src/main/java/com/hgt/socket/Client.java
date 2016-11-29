package com.hgt.socket;

/**
 * 客户端类
 * <p>
 * 功能：
 *
 * @author Administrator
 */
public class Client extends Thread {

    @Override
    public void run() {
        try {
            while (true) {
                // 获得ClientSender实例后，调用send()方法发送数据
                ClientSender.getInstance().send();
                synchronized (Client.class) {
                    Thread.sleep(2000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}