package com.ghj.after.rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * <p>
 * Description: 测试StampedLock
 * <p>
 * 优点： 拥有乐观读，读的时候可以进行写操作
 * 缺点： 1、不可重入，重入会死锁 2、不支持使用Condition 3、使用时不要进行中断操作（interrupt）
 *
 * @author guohuanjun1
 * @date 2024/3/3 21:35
 */
public class StampedLockTest {

    private static int num = 33;

    static StampedLock stampedLock = new StampedLock();

    /**
     * 悲观读
     *
     * @return
     * @throws InterruptedException
     */
    public static int read() throws InterruptedException {
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "read start .....");
        try {
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "read value" + num);
            }
        } finally {
            stampedLock.unlockRead(stamp);
        }
        System.out.println(Thread.currentThread().getName() + "read end .....");
        return num;
    }

    /**
     * 写（悲观）
     *
     * @param adder
     */
    public static void write(int adder) {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "write start .....");
        num = num + adder;
        stampedLock.unlockWrite(stamp);
        System.out.println(Thread.currentThread().getName() + "write end .....");
    }

    public static int tryOptimisticRead() throws InterruptedException {
        long stamp = stampedLock.tryOptimisticRead();
        System.out.println(Thread.currentThread().getName() + "tryOptimisticRead start .....");
        int result = num;
        for (int i = 0; i < 5; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + "乐观验证值：" + stampedLock.validate(stamp) + ", read value" + num);
        }
        // 表示读的过程中值被修改，锁升级为悲观读锁
        if (!stampedLock.validate(stamp)) {
            System.out.println("值已经被修改，锁升级悲观锁");
            stamp = stampedLock.readLock();
            result = num;
            System.out.println("修改后的值：" + result);
            stampedLock.unlockRead(stamp);
        }
        return result;
    }

    public static void main(String[] args) throws InterruptedException {

//        // 普通读
//        new Thread( () -> {
//            try {
//                StampedLockTest.read();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "read thread").start();
//
//        TimeUnit.SECONDS.sleep(1);
//
//        // 普通写
//        new Thread( () -> {
//            StampedLockTest.write(22);
//        }, "write thread").start();

        // 乐观读
        new Thread( () -> {
            try {
                StampedLockTest.tryOptimisticRead();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "OptimisticRead thread").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread( () -> {
            StampedLockTest.write(44);
        }, "write thread").start();
    }
}
