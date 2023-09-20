package com.ghj.after.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Description: CompletableFuture 继承Future、CompletionStage接口，可以直接替代FutureTask，并且拥有更好的操作
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/9/20 23:30
 */
public class CompletableFutureDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        /*
         runAsync和supplyAsync一个参数是Runnable，一个是Supplier，后者拥有返回值，当没有传入第二个参数Executor时，
         使用的是默认的ForkJoinPool执行线程，当主线程结束，该线程池会关闭，也就是说该线程池开启的线程是守护线程，因此建议自定义线程池
         */
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "eee";
        });
        System.out.println(runAsync.get());
        System.out.println(supplyAsync.get());
    }
}
