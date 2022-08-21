package com.ghj.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author 86187
 * 关于锁的问题
 * 先打电话， 再发短信
 */
public class Test05 {
    public static void main(String[] args) {
        Phone5 phone1 = new Phone5();
        Phone5 phone2 = new Phone5();
        new Thread(() -> {
            phone1.sendSm();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}
class Phone5{
    //当这里加上了static，那就是锁的Class对象，每个类只有一个Class对象
    // Class<Phone4> aClass = Phone4.class;
    public static synchronized void sendSm(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    //这里锁的是调用的对象， 因此不会因为锁住了class模板而卡住
    public synchronized void call(){
        System.out.println("打电话");
    }
}
