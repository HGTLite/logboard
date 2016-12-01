package com.hgt;


import com.hgt.heartbeat.HeartBeatServer;

public class TaskApp
{

    public static void main( String[] args )
    {
        System.out.println("开始检测客户端是否在线...");
        new HeartBeatServer().start();
    }

}
