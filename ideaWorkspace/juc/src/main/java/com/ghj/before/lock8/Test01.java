package com.ghj.before.lock8;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 86187
 * 关于锁的问题
 * 方法上的锁是将调用该方法的对象锁住
 * 下面测试始终是发短信先获取锁， 因为中间主线程有睡眠1秒， 在发短信时候不管睡眠多久， 锁都是在发短信身上， 必须执行完发短信， 打电话才能获取锁
 */
public class Test01 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSm();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.call();
        }, "B").start();
        new Thread(() -> {
            Phone.sayHello();
        }).start();
    }
}
class Phone{
    private static final ReentrantLock lock = new ReentrantLock();
    private  ReentrantLock sLock = new ReentrantLock();
    public void sendSm(){
        sLock.lock();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            sLock.unlock();
        }
        System.out.println("发短信");
    }
    public void call(){
        sLock.lock();
        try {
            System.out.println("打电话");
        } finally {
            sLock.unlock();
        }
    }
    public static void sayHello(){
        lock.lock();
        try {
            System.out.println("hello");
        } finally {
            lock.unlock();
        }
    }
}
