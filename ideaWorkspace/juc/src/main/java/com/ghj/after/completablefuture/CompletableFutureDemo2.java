package com.ghj.after.completablefuture;

import com.ghj.after.utils.ThreadPollExecutorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Description: whenComplete任务完成的回调， exceptionally为任务抛出异常之后的回调
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/9/20 23:48
 */
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        List<String> list = new ArrayList<>();
        list.add("start");
        ExecutorService threadPoll = ThreadPollExecutorUtil.getThreadPoll();
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                //throw new Exception("hhh");
            } catch (Exception e) {
                e.printStackTrace();
            }
            int a = 10 /0;
            return "over";
        }, threadPoll);
        // whenComplete当任务执行完成之后的回调方法，v为任务执行的返回值，e为异常， 即使出现异常也会执行whenComplete；
        // exceptionally为任务抛出异常之后的回调
        completableFuture.whenComplete((v, e) -> {
            if(e == null){
                list.add(v);
                System.out.println("执行 ===> whenComplete");
            }
        }).exceptionally(e -> {
            System.out.println("出异常拉!");
            System.out.println("执行 ===> exceptionally");
            return "exception";
        });
        threadPoll.shutdown();
        test(list);
        TimeUnit.SECONDS.sleep(5);
        System.out.println(list);
        long end = System.currentTimeMillis();
        System.out.println("cost time ==> " + (end - start) + " ms");
    }
    public static void test(List<String> list) throws InterruptedException {
        System.out.println("test==start===> " + list);
        TimeUnit.SECONDS.sleep(4);
        System.out.println("test==end===> " + list);
    }
}
