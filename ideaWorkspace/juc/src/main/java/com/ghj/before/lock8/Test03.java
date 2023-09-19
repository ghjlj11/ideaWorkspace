package com.ghj.before.lock8;

import java.util.concurrent.TimeUnit;

/**
 * @author 86187
 * 关于锁的问题
 * 方法上的锁是将调用该方法的对象锁住， 这里有两个对象， 因此phone1的锁被拿走了， phone2的锁一样可以获取， 因此先执行打电话， 再发短信
 * 先打电话， 再发短信
 */
public class Test03 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();
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
class Phone3{
    public synchronized void sendSm(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
}
