package com.ghj.before.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 86187
 * volatile保证可见性， 不保证原子性，禁止指令重排
 *
 */
public class VolatileDemo {
    /**
     *使用juc下的atomic包下的AtomicInteger可以保证原子性
     */
    public static volatile AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    num.getAndIncrement();
                }
            }).start();
        }

        //获取存活线程数量， java默认会有main，GC线程两个
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        //很多时候不是20000
        System.out.println(Thread.currentThread().getName() + "=>" + num);
    }
}
