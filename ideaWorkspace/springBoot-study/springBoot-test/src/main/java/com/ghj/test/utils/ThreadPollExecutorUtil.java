package com.ghj.test.utils;

import java.util.concurrent.*;

/**
 * <p>
 * Description: 线程池工具类
 * </p>
 *
 * @author guohuanjun1
 * @date 2023/9/19 22:56
 */
public class ThreadPollExecutorUtil {

    public static ExecutorService getThreadPoll() {
        return new ThreadPoolExecutor(3,
                3,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
