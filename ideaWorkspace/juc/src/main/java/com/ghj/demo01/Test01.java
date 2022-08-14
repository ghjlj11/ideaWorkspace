package com.ghj.demo01;

import java.util.concurrent.TimeUnit;

/**
 * @author 86187
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        //查看CPU最大线程数
        System.out.println(Runtime.getRuntime().availableProcessors());

        //使用TimeUnit让线程睡眠3秒
        TimeUnit.SECONDS.sleep(3);
    }
}
