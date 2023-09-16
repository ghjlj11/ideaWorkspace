package com.ghj.future;

import java.util.concurrent.*;

/**
 * @author 86187
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1234;
        });
        long s = System.currentTimeMillis();
        Integer integer = future.get();
        long e = System.currentTimeMillis();
        System.out.println(integer);
        System.out.println(e - s);
    }
}
