package com.ghj.theVolatile;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 86187
 */
public class Test extends Thread{

    private Lock lock = new ReentrantLock();
    private  String i ="sss";
    private volatile int n = 0;

    private void setI(String i){

        this.i=i;

    }

    @Override

    public void run() {

        System.out.println(Thread.currentThread().getName()+"进入方法"+i);

        lock.lock();
        try {
        while (n < 200){




                Thread.sleep(10);
                System.out.println(Thread.currentThread().getName()+"方法执行");
                System.out.println(Thread.currentThread().getName() + "+ n =" + n ++);


//             try {
//
//             Thread.sleep(3000);
//
//             } catch (InterruptedException e) {
//
//             e.printStackTrace();
//
//             }

        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }

        System.out.println(Thread.currentThread().getName() + "线程结束");

    }

    public static void main(String[] args ) throws InterruptedException{

        Test test=new Test();


        new Thread(test,"001").start();
        new Thread(test,"002").start();
        new Thread(test,"003").start();
        new Thread(test,"004").start();
        new Thread(test,"005").start();
        new Thread(test,"006").start();
        new Thread(test,"007").start();
        new Thread(test,"008").start();


//        test.setI("线程设置了stop");
//        Thread.sleep(1000);
//
//        System.out.println("线程设置了stop");

    }

}