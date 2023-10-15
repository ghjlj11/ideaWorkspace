package com.ghj.after.deadlock;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/10/15 17:02
 */
public class ResourceTest {
    private final String name1 = new String("111");
    private final String name2 = new String("222");
    Lock lock = new ReentrantLock();
    public void test1 () throws InterruptedException {
        synchronized (name1) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + ": test01 :" + name1);
            synchronized (name2) {
                System.out.println(Thread.currentThread().getName() + ": test01 :" + name2);
            }
        }
    }
    public void test2 () throws InterruptedException {
        synchronized (name2) {
            System.out.println(Thread.currentThread().getName() + ": test02 : " + name2);
            synchronized (name1) {
                System.out.println(Thread.currentThread().getName() + ": test02 :" + name1);
            }
        }
    }
}
