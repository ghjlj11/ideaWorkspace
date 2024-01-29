package com.ghj.after.utils;

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

    private static final int CORE_THREAD = Runtime.getRuntime().availableProcessors();
    public static ExecutorService getThreadPoll() {
        return new ThreadPoolExecutor(CORE_THREAD * 2,
                CORE_THREAD * 3,
                5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(CORE_THREAD),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
