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
     * 如果只是单纯的volatile修饰的int类型，多线程累加结果会与预期不一致，只有使用AtomicInteger修饰，才可以保证原子性
     * volatile只是保证每个线程读的数据是最新的，如果多线程执行volatile++操作，
     * 比如当前volatile值为5，一个线程读到了5加1为6，此时还没有赋值进去，另一个线程读到了5也加1为6，两个+1操作就会变成只+1。
     */
    public static AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    num.incrementAndGet();
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
