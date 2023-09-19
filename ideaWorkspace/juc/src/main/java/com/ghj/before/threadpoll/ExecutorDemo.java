package com.ghj.before.threadpoll;

import java.util.concurrent.*;

/**
 * @author 86187
 * Executors
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        //创建一个单一的线程池
        ExecutorService threadPoll = Executors.newSingleThreadExecutor();
        //创建指定大小的线程池
        threadPoll = Executors.newFixedThreadPool(5);
        //无上限，系统能执行多少就多少
        threadPoll = Executors.newCachedThreadPool();

        //获取当前服务器的最大线程数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 0; i < 9; i++) {
                poolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "===>");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }
    }
}
