package com.ghj.after.threadlocal;

import com.ghj.after.utils.ThreadPollExecutorUtil;

import java.util.concurrent.ExecutorService;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/10/22 20:45
 */
public class TestThreadLocal {
    public static void main(String[] args) {
        House house = new House();
        ExecutorService threadPoll = ThreadPollExecutorUtil.getThreadPoll();
        try {
            for (int i = 0; i < 10; i++) {
                threadPoll.submit( () -> {
                    try {
                        Integer before = house.threadLocal.get();
                        house.add();
                        Integer after = house.threadLocal.get();
                        System.out.println(Thread.currentThread().getName() + "--before:" + before + "--after: " + after );
                    } finally {
                        // 线程池场景下使用完需要移除
                        house.threadLocal.remove();
                    }
                });
            }
        } finally {
            threadPoll.shutdown();
        }
    }
}
