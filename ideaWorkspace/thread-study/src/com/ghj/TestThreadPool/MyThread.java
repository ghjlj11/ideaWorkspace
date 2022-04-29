package com.ghj.TestThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 86187
 */
public class MyThread implements Runnable{
    @Override
    public void run() {
        for(int i = 0 ; i < 10 ; i ++){
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}

class Main{
    public static void main(String[] args) {
        ExecutorService sre = Executors.newFixedThreadPool(3);
        sre.execute(new MyThread());
        sre.execute(new MyThread());
        sre.execute(new MyThread());
    }
}
