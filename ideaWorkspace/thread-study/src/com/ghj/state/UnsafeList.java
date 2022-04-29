package com.ghj.state;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
public class UnsafeList extends Thread{
    static List<String> list = new ArrayList<>();
    @Override
    public void run() {
        synchronized (list){
            list.add(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UnsafeList unsafeList = new UnsafeList();
        for(int i = 0 ; i < 10000 ; i ++){
            new Thread(unsafeList,"i").start();
        }
        Thread.sleep(2000);
        System.out.println(list.size());
    }
}
