package com.ghj.after.cas;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 * Description: 自旋锁
 * 会出现ABA问题，即如果一个线程将value由A修改为B之后，又来一个线程将value由B修改为A，此时一直处于等待的线程会觉得value一直没有被修改
 * 使用AtomicStampedReference，会带有一个流水号，可以根据流水号，解决ABA问题
 * 使用AtomicMarkableReference，会带有一个boolean标记，表示是否被修改，解决原子引用类是否被修改的问题，但不能解决多次修改问题
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/12/25 22:57
 */
public class MySpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock () {
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)) {}
        System.out.println(thread.getName() + " come in ......");
    }

    public void unlock () {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " over ......");
        atomicReference.compareAndSet(thread, null);
    }
}
