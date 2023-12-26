package com.ghj.after.atomic;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/12/25 23:02
 */
public class TestMySpinLock {
    public static void main(String[] args) throws InterruptedException {
        MySpinLock mySpinLock = new MySpinLock();
        new Thread( () -> {
            mySpinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySpinLock.unlock();
        }, "A").start();
        TimeUnit.SECONDS.sleep(1);
        new Thread( () -> {
            mySpinLock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySpinLock.unlock();
        }, "B").start();
    }
}
