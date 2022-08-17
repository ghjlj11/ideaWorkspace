package com.ghj.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 86187
 * 使用lock的方法， 先new一个lock， 选择实现类， 然后在需要上锁的代码前面加上lock.lock()方法，然后把正常的代码放在try里面
 * 在finally使用lock.unlock解锁
 */
public class SaleTicket2 {
    public static void main(String[] args) {
    Tickets2 tickets = new Tickets2();
    new Thread(() -> {
        for (int i = 0; i < 40; i++) {
            tickets.sale();
        }
    }, "A").start();
    new Thread(() -> {
        for (int i = 0; i < 40; i++) {
            tickets.sale();
        }
    }, "B").start();
    new Thread(() -> {
        for (int i = 0; i < 40; i++) {
            tickets.sale();
        }
    }, "C").start();
}
}
class Tickets2 {
    private int num = 30;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if(num > 0){
                System.out.println("num===>" + num--);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            lock.unlock();
        }
    }
}
