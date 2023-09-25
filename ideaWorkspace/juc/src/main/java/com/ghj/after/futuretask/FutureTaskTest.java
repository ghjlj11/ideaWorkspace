package com.ghj.after.futuretask;

import com.ghj.after.utils.ThreadPollExecutorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 * Description: FutureTask，继承了Runnable, Future接口，同时构造函数入参可以传入Callable接口，集成一体
 * </p>
 * FutureTask的缺点就是调用get方法时，主线程处于阻塞状态，耗费时间；或者通过轮询调用isDone方法判断线程是否执行完毕，执行完成之后再调用get
 * 方法，也是一个不好的方式
 * @author guohuanjun1
 * @date 2023/9/19 22:41
 */
public class FutureTaskTest {
    public static void test(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService threadPoll = ThreadPollExecutorUtil.getThreadPoll();
        FutureTask<String> futureTask1 = new FutureTask<>(new MyCallable(2));
        FutureTask<String> futureTask2 = new FutureTask<>(new MyCallable(3));
        FutureTask<String> futureTask3 = new FutureTask<>(new MyCallable(4));
        threadPoll.submit(futureTask1);
        threadPoll.submit(futureTask2);
        threadPoll.submit(futureTask3);

        // 注意 Future的get方法一定要在线程启动之后调用，否则会直接阻塞，卡死
        String s1 = futureTask1.get();
        String s2 = futureTask2.get();
        String s3 = futureTask3.get();
        System.out.println(s1 );
        threadPoll.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + " ms");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPoll = ThreadPollExecutorUtil.getThreadPoll();
        List<MyCallable> taskList = new ArrayList<>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 125; i++) {
            MyCallable myCallable = new MyCallable(1);
            taskList.add(myCallable);
        }
        List<Future<String>> futures = threadPoll.invokeAll(taskList);
        for (Future<String> future : futures) {
            String s = future.get();
            System.out.println(s);
        }
        TimeUnit.SECONDS.sleep(5);
        long end = System.currentTimeMillis();
        System.out.println("spend time ===> " + (end - start) + " ms");
        threadPoll.shutdown();
    }
}
