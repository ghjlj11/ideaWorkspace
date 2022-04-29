package com.ghj.state;

/**
 * @author 86187
 */
public class TestPriority implements Runnable{
    @Override
    public void run() {
        int priority = Thread.currentThread().getPriority();
        System.out.println("Thread - " + Thread.currentThread().getName() + "-->" + priority);
    }

    public static void main(String[] args) {
        TestPriority t = new TestPriority();
        Thread t1 = new  Thread(t,"t1");
        Thread t2 = new  Thread(t,"t2");
        Thread t3 = new  Thread(t,"t3");
        Thread t4 = new  Thread(t,"t4");

        t1.setPriority(1);
        t2.setPriority(4);
        t3.setPriority(6);
        t4.setPriority(10);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
