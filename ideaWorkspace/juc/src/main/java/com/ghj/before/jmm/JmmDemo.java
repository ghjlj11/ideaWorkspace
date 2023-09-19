package com.ghj.before.jmm;

import java.util.concurrent.TimeUnit;

/**
 * @author 86187
 * 测试jmm， 线程会阻塞
 */
public class JmmDemo {
    public static int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (num == 0){

            }
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //此时虽然修改了num， 但是线程并没有再次去读主内存里的值， 因此会阻塞
        num = 1;
    }
}
