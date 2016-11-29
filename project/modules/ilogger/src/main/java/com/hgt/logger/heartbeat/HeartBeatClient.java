package com.hgt.logger.heartbeat;

/**
 * 发送心跳类， 10 秒/次
 */
public class HeartBeatClient extends Thread {

    @Override
    public void run() {
        try {
            while (true) {
                // 获得ClientSender实例后，调用send()方法发送数据
                ClientSender.getInstance().send();
                synchronized (HeartBeatClient.class) {
                    Thread.sleep(3000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        HeartBeatClient client = new HeartBeatClient();
//        client.start();
//    }

}