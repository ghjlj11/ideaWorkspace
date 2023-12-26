package com.ghj.after.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * Description: 测试CountDownLatch
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/12/26 22:44
 */
public class TestCountDownLatch {
    private static final int SIZE = 50;
    private static final AtomicInteger ATOMICINTEGER = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(SIZE);
        for (int i = 0; i < SIZE; i++) {
            new Thread( () -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        ATOMICINTEGER.getAndIncrement();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }
        // 当前计数大于0，则当前线程等待，主线程可以在所有线程完成累加操作后，马上获取最新值
        countDownLatch.await();
        System.out.println(ATOMICINTEGER.get());
    }
}
