package com.hgt.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/******************************************************************************
 * Created by  Yao  on  2016/9/28
 * README: 测试线程
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class TESTThread {

    public static void main(String[] args) {

        /**定时线程池**/
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds 首次延迟1s执行, and excute every 3 seconds执行时间间隔");
            }
        }, 1, 3, TimeUnit.SECONDS);
//        scheduledThreadPool.schedule(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("delay 3 seconds");
//            }
//        }, 3, TimeUnit.SECONDS);

        /**定长线程池**/
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            fixedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(index);
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }


        /**缓存线程池**/
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            try {
//                Thread.sleep(index * 300);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
////            System.out.println(index);
//            cachedThreadPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(index);
//                }
//            });
//        }

        /**单线程**/
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        for (int i = 0; i < 5; i++) {
//            final int index = i;
//            singleThreadExecutor.execute(new Runnable() {
//
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(index);
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }


    }
}
