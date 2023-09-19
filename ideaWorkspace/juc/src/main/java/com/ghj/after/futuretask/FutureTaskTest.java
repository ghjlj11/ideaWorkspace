package com.ghj.after.futuretask;

import com.ghj.after.utils.ThreadPollExecutorUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 * Description: FutureTask，继承了Runnable, Future接口，同时构造函数入参可以传入Callable接口，集成一体
 * </p>
 * @author guohuanjun1
 * @date 2023/9/19 22:41
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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
}
