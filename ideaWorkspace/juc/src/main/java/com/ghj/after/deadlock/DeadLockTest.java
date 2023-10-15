package com.ghj.after.deadlock;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/10/15 17:01
 */
public class DeadLockTest {

    public static void main(String[] args) {
        ResourceTest resourceTest = new ResourceTest();
        Object name1 = new Object();
        Object name2 = new Object();
        new Thread(() -> {
            try {
                resourceTest.test1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();
        new Thread(() -> {
            try {
                resourceTest.test2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
