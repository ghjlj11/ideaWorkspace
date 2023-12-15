package com.ghj.after.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * <p>
 * Description:
 * 对于等待/通知线程，之前可以使用线程的wait、notify方法以及 condition的await和signal方法
 * 以上方法都需要获取锁，而LockSupport简化了代码编辑，不需要获取锁实现等待/通知线程
 * pack方法，即是阻塞线程，需要线程获取通行证才可以继续执行，并且会消耗改通行证；
 * unpack方法就是给线程颁发通行证，一个线程最多只能有一个通行证
 * 无论线程在pack之前还是之后获取到了通行证，都是可以继续执行的
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/12/15 22:41
 */
public class LockSupportTest {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1   come  in.....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            System.out.println("thread1 被唤醒");
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LockSupport.unpark(thread1);
            System.out.println("thread2 发出通知");
        });
        thread2.start();
    }
}
