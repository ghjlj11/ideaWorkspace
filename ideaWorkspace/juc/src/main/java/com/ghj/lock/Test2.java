package com.ghj.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 86187
 * 生产者与消费者Lock版本
 */
public class Test2 {
    public static void main(String[] args) {
        A a = new A();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    a.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    a.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    a.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    a.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
class A{
    public int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Condition condition1 = lock.newCondition();
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0){
                //相当于wait方法
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "===>" + num);
            num ++;
            //相当于notify方法
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "===>" + num);
            num --;
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
