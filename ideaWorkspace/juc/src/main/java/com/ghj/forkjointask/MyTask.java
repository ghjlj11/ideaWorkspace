package com.ghj.forkjointask;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author 86187
 */
public class MyTask extends RecursiveTask<Integer> {
    private final Integer ADJUST = 10 ;
    private Integer begin;
    private Integer end;
    private Integer result = 0;

    public MyTask(Integer begin, Integer end){
        this.begin = begin;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if(end - begin < ADJUST){
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        }
        else {
            int mid = (end - begin) / 2 + begin;
            MyTask myTask1 = new MyTask(begin, mid);
            MyTask myTask2 = new MyTask(mid + 1, end);
            // 调用fork()方法也会执行compute()
            myTask1.fork();
            myTask2.fork();
            result = myTask1.join() + myTask2.join();
        }
        return result;
    }
}
