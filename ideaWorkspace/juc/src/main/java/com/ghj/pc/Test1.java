package com.ghj.pc;

/**
 * @author 86187
 * 生产者与消费者
 */
public class Test1 {
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
    public synchronized void increment() throws InterruptedException {
        while (num != 0){
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "===>" + num);
        num ++;
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        while (num == 0){
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "===>" + num);
        num --;
        this.notifyAll();
    }
}
