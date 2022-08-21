package com.ghj.helpclass;

import java.util.concurrent.Semaphore;

/**
 * @author 86187
 * Semaphore设置一个资源数， 线程可以去获取资源也可以释放资源， 如果资源全被获取， 那么需要拿取资源的线程就需要等待有线程释放资源
 * 可以用来限流
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    //拿取资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "==>获取到了资源");
                    //释放资源
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "==>释放了了资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
