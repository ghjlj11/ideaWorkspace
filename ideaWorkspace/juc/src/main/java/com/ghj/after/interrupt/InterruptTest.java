package com.ghj.after.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 * Description: 中断测试
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/12/10 22:39
 */
public class InterruptTest {

    static volatile boolean stop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) throws Exception{
        //testVolatile();
        //testAtomic();
        testInterrupt();
    }

    /**
     * 利用volatile中断
     * @throws Exception
     */
    static void testVolatile () throws Exception{
        new Thread( () -> {
            while (true) {
                System.out.println("haha!");
                if (stop) {
                    System.out.println("I am stop");
                    break;
                }
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(50);
        new Thread( () -> {
            stop = true;
        }).start();
    }

    /**
     * AtomicBoolean测试
     * @throws Exception
     */
    static void testAtomic () throws Exception{
        new Thread( () -> {
            while (true) {
                System.out.println("haha!");
                if (atomicBoolean.get()) {
                    System.out.println("I am stop");
                    break;
                }
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(50);
        new Thread( () -> {
            atomicBoolean.set(true);
        }).start();
    }

    /**
     * interrupt测试
     * interrupt方法仅仅只是将线程中的一个标记设置为true，不会中断线程，需要配合业务代码检索该标记，实现中断
     * 如果被调用interrupt的线程处于阻塞状态（处于jion、sleep、wait）,那么将会抛出异常，标记不会被改成true
     * isInterrupted方法获取中断标识，当线程处于不活动状态，那么返回false
     * Thread.interrupted();方法会返回当前线程的中断标志，并且清除该标志（设置为false），如果连续两次调用，第二次直接返回false
     * @throws Exception
     */
    static void testInterrupt () throws Exception {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("haha!");
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("I am stop");
                    break;
                }
            }
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(50);
        thread.interrupt();
    }
}
