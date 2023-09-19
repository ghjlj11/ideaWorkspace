package com.ghj.before.helpclass;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 86187
 * CyclicBarrier也是一个计数器， 当达到一定值时候就会执行一个线程
 * 这个值需要每次调用cyclicBarrier.await()就会加1， 调用同时也会让使用此方法的线程进入阻塞
 * 当达到这个规定的值的时候， 指定的线程会自动执行。
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7 , () -> {
            System.out.println("芝麻开门");
        });

        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + "===>" + temp);
                try {
                    cyclicBarrier.await();
                    System.out.println("现在是===>" + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
