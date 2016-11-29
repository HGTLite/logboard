package com.hgt.socket;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端类
 * 功能:
 * @author Administrator
 *
 */
public class Server extends Thread{

    //This class implements server sockets
    private ServerSocket server = null;

    Object obj = new Object();

    @Override
    public void run() {

        try {
            //  Creates a server socket, bound to the specified port
            server = new ServerSocket(11234);
            while(true) {
                // This class implements client sockets (also called just"sockets").
                // A socket is an endpoint for communication between two machines.
                Socket client = server.accept();// Listens for a connection to be made to this socket and accepts it.
                synchronized(obj) {
                    new Thread(new Client(client)).start(); // 客户端线程启动
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 客户端线程
     * @author Administrator
     *
     */
    class Client implements Runnable {

        Socket client;


        public Client(Socket client) {

            this.client = client;
        }


        @Override
        public void run() {
            try {
                while(true) {
					/*
					 * getInputStream():Returns an input stream for this socket
					 * ObjectInputStream():Creates an ObjectInputStream that reads from the specified InputStream.
					 */
                    ObjectInput in = new ObjectInputStream(client.getInputStream());
                    Entity entity = (Entity) in.readObject();
                    System.out.println(entity);// 输出心跳包中的信息
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        System.out.println("开始检测客户端是否在线...");
        new Server().start();
    }

}