package com.ghj.after.atomic;

import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * <p>
 * Description: 测试AtomicReferenceFieldUpdater
 * 效果类似AtomicIntegerFieldUpdater，将引用类型的属性设置为原子属性
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/4 23:16
 */
public class AtomicReferenceFieldUpdaterTest {
    public static void main(String[] args) {
        MySource mySource = new MySource();
        for (int i = 0; i < 5; i++) {
            new Thread( () -> {
                mySource.wantToUse(mySource);
            }).start();
        }
    }
}

class MySource {
    private volatile String use = "no use";
    private AtomicReferenceFieldUpdater<MySource, String> referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(MySource.class, String.class, "use");

    public void wantToUse (MySource mySource) {
        if (referenceFieldUpdater.compareAndSet(mySource, "no use", "using")) {
            System.out.println(Thread.currentThread().getName() + "使用中 .....");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "使用结束");
        }
        else {
            System.out.println(Thread.currentThread().getName() + "其他线程正在使用");
        }
    }
}
