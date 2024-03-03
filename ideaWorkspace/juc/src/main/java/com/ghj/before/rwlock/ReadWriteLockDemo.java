package com.ghj.before.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 86187
 * 读写锁
 * 当写锁被线程获取没有释放时，其他线程进行读或者写操作都会被阻塞
 * 当读锁被线程获取没有释放时，其他线程写会被阻塞，读则可以继续
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Catch aCatch = new Catch();
        new Thread( () -> {
            aCatch.get("0");
        }, "read fst").start();
        TimeUnit.SECONDS.sleep(1);
        //写入操作
        for (int i = 0; i < 1; i++) {
            final int t = i;
            new Thread(() -> {
                aCatch.get("0");
                aCatch.put(t + "", t + "");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                aCatch.get("0");
            }, i + "write").start();
        }
        //读取操作
        for (int i = 0; i < 1; i++) {
            final int t = i;
            new Thread(() -> {
                aCatch.get(t + "");
            }, i + "read").start();
        }
    }
}
class Catch{
    private volatile Map<String, String> map = new HashMap<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String k, String v) {
        //写锁， 只可以有一个线程写
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "==>进入到写操作");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(k, v);
        System.out.println(Thread.currentThread().getName() + "==>写操作  ok");
        readWriteLock.writeLock().unlock();
    }
    public void get(String k){
        //读锁， 可以多个线程读， 但是不可以写
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "==>进入到读操作");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.get(k);
        System.out.println(Thread.currentThread().getName() + "==>读操作  ok");
        readWriteLock.readLock().unlock();
    }
}
