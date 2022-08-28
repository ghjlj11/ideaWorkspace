package com.ghj.future;

import java.util.concurrent.CompletableFuture;

/**
 * @author 86187
 */
public class FutureDemo {
    public static void main(String[] args) {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 1234;
        });
    }
}
