package com.ghj.before.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 官网的例子
 */
class BoundedBuffer {
   final Lock lock = new ReentrantLock();
   final Condition notFull  = lock.newCondition();
   final Condition notEmpty = lock.newCondition(); 

   final Object[] items = new Object[5];
   int putptr, takeptr, count;

   public void put(Object x) throws InterruptedException {
     lock.lock(); try {
       while (count == items.length)
         notFull.await();
       items[putptr] = x;
       if (++putptr == items.length) putptr = 0;
       ++count;
       System.out.println(Thread.currentThread().getName() + "存放了了第" + putptr + "个， 是====>" + x);
       notEmpty.signal();
     } finally { lock.unlock(); }
   }

   public Object take() throws InterruptedException {
     lock.lock(); try {
       while (count == 0)
         notEmpty.await();
       Object x = items[takeptr];
       if (++takeptr == items.length) takeptr = 0;
       --count;
       System.out.println(Thread.currentThread().getName() + "拿取了第" + count + "个， 是====>" + x);
       notFull.signal();
       return x;
     } finally { lock.unlock(); }
   }
 } 