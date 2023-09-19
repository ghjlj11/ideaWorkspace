package com.ghj.before.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author 86187
 * 关于锁的问题
 * 先发短信， 再打电话
 */
public class Test04 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
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
class Phone4{
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
    public static synchronized void call(){
        System.out.println("打电话");
    }
}
