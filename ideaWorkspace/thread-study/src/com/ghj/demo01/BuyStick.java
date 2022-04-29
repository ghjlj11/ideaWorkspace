package com.ghj.demo01;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 86187
 */
public class BuyStick implements Runnable{

    private  int stick = 10;
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public  void  run() {
        while (true){
            lock.lock();
            try{
                if(stick <= 0){
                    break;
                }
                System.out.println(Thread.currentThread().getName() + "拿到了第" + stick -- +"张票");
            }
            finally {
                lock.unlock();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BuyStick t = new BuyStick();
        new Thread(t,"1").start();
        new Thread(t,"2").start();
        new Thread(t,"3").start();
    }
}
