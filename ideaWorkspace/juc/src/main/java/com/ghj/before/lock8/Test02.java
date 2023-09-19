package com.ghj.before.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author 86187
 * 关于锁的问题
 * 方法上的锁是将调用该方法的对象锁住
 * 当执行没有被锁的方法， 那么就不需要拿取到对象的锁， 先执行hello
 */
public class Test02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(() -> {
            phone.sendSm();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.sayHello();
        }, "B").start();
    }
}
class Phone2{
    public synchronized void sendSm(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
    public void sayHello(){
        System.out.println("hello");
    }
}
