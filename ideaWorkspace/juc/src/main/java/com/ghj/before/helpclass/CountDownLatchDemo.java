package com.ghj.before.helpclass;

import java.util.concurrent.CountDownLatch;

/**
 * @author 86187
 * CountDownLatch是一个计数的辅助类， 只能做减1操作
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(8);
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                //让CountDownLatch里的值减1
                System.out.println(Thread.currentThread().getName() + "==>" + "xxxxxx");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //当CountDownLatch里的值变为0才会继续走下去
        countDownLatch.await();
        System.out.println("haha");
    }
}
