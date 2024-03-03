package com.ghj.after.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * Description:  AbstractQueuedSynchronizer
 * ReentrantLock锁、CountDownLatch、Semaphore等底层实现线程对资源的竞争、线程的阻塞与唤醒、就是使用AbstractQueuedSynchronizer实现
 * <p>
 *     以ReentrantLock锁的lock（公平锁与非公平类似）方法为例，当多个线程尝试获取锁，即调用lock方法时；
 *     首先第一个线程会直接获取到锁，调用aqs中的acquire()
 * @author guohuanjun1
 * @date 2024/2/25 20:24
 */
public class TestAqs {

    Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        TestAqs testAqs = new TestAqs();
        new Thread( () -> {
            testAqs.lock.lock();
            try {
                System.out.println("Thread 1 using");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                testAqs.lock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread( () -> {
            testAqs.lock.lock();
            try {
                System.out.println("Thread 2 using");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                testAqs.lock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread( () -> {
            testAqs.lock.lock();
            try {
                System.out.println("Thread 3 using");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                testAqs.lock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread( () -> {
            testAqs.lock.lock();
            try {
                System.out.println("Thread 4 using");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                testAqs.lock.unlock();
            }
        }).start();
    }
}
