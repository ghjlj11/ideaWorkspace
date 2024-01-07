package com.ghj.after.longadder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * <p>
 * Description: 在高并发环境下LongAdder与LongAccumulator比AtomicLong速度快，更推荐使用
 * 50个线程每个加一千万，LongAdder与LongAccumulator比AtomicLong速度快10倍
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/6 22:52
 */
public class LongAdderTest {
    static final int W_1000 = 10000000;
    static final int SIZE = 50;
    int count = 0;
    AtomicLong atomicLong = new AtomicLong(0);
    LongAdder longAdder = new LongAdder();
    LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);

    synchronized void add () {
        count ++;
    }

    void atomicLongAdd () {
        atomicLong.incrementAndGet();
    }

    void longAdderAdd () {
        longAdder.increment();
    }

    void longAccumulatorAdd () {
        longAccumulator.accumulate(1);
    }

    public static void main(String[] args) throws InterruptedException {
        LongAdderTest longAdderTest = new LongAdderTest();
        long startTime, endTime;
        CountDownLatch countDownLatch1 = new CountDownLatch(SIZE);
        CountDownLatch countDownLatch2 = new CountDownLatch(SIZE);
        CountDownLatch countDownLatch3 = new CountDownLatch(SIZE);
        CountDownLatch countDownLatch4 = new CountDownLatch(SIZE);

        // sync 测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < W_1000; j++) {
                        longAdderTest.add();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }).start();
        }
        countDownLatch1.await();
        endTime = System.currentTimeMillis();
        System.out.println(" add () 完成, 耗时：" + (endTime - startTime) + "ms，" + "结果：" + longAdderTest.count);

        // atomicLong测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread( () -> {
                try {
                    for (int j = 0; j < W_1000; j++) {
                        longAdderTest.atomicLongAdd();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }).start();
        }
        countDownLatch2.await();
        endTime = System.currentTimeMillis();
        System.out.println(" atomicLongAdd () 完成, 耗时：" + (endTime - startTime) + "ms，" + "结果：" + longAdderTest.atomicLong.get());

        // longAdder测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread( () -> {
                try {
                    for (int j = 0; j < W_1000; j++) {
                        longAdderTest.longAdderAdd();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }).start();
        }
        countDownLatch3.await();
        endTime = System.currentTimeMillis();
        System.out.println(" longAdderAdd () 完成, 耗时：" + (endTime - startTime) + "ms，" + "结果：" + longAdderTest.longAdder.sum());

        // longAccumulator测试
        startTime = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            new Thread( () -> {
                try {
                    for (int j = 0; j < W_1000; j++) {
                        longAdderTest.longAccumulatorAdd();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }).start();
        }
        countDownLatch4.await();
        endTime = System.currentTimeMillis();
        System.out.println(" longAccumulatorAdd () 完成, 耗时：" + (endTime - startTime) + "ms，" + "结果：" + longAdderTest.longAccumulator.get());

    }
}
