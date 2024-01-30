package com.ghj.after.locksupport;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Description: Semaphore是一个计数信号量，用来管理一定数量的许可证。每个线程在访问共享资源之前，需要先获取一个许可证，
 * 如果许可证已经被其他线程占用，则需要等待，直到许可证可用。当线程使用完共享资源后，需要释放许可证，使其他线程可以继续访问。
 * Semaphore semaphore = new Semaphore(int permits);其中permits表示许可证的数量，即同一时间内允许的并发访问线程数。
 * semaphore.acquire(); 线程获取许可证，如果有可用的许可证，则线程可以继续执行；否则，线程将进入阻塞状态，直到有许可证可用。
 * semaphore.release(); 线程在使用完共享资源后，应该释放许可证，以便其他线程可以获取许可证。
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/1/30 23:53
 */

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        Thread thread1 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("thread1   come  in.....");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("thread2   come  in.....");
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        });
        thread2.start();
    }
}
