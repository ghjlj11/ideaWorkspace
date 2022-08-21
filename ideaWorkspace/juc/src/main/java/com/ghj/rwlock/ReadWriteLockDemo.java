package com.ghj.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 86187
 * 读写锁
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Catch aCatch = new Catch();
        //写入操作
        for (int i = 0; i < 5; i++) {
            final int t = i;
            new Thread(() -> {
                aCatch.put(t + "", t + "");
            }, i + "").start();
        }
        //读取操作
        for (int i = 0; i < 5; i++) {
            final int t = i;
            new Thread(() -> {
                aCatch.get(t + "");
            }, i + "").start();
        }
    }
}
class Catch{
    private volatile Map<String, String> map = new HashMap<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String k, String v){
        //写锁， 只可以有一个线程写
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "==>进入到写操作");
        map.put(k, v);
        System.out.println(Thread.currentThread().getName() + "==>写操作  ok");
        readWriteLock.writeLock().unlock();
    }
    public void get(String k){
        //读锁， 可以多个线程读， 但是不可以写
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "==>进入到读操作");
        map.get(k);
        System.out.println(Thread.currentThread().getName() + "==>读操作  ok");
        readWriteLock.readLock().unlock();
    }
}
