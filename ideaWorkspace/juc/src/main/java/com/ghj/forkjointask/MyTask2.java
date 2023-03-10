package com.ghj.forkjointask;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveTask;

/**
 * @author 86187
 */
public class MyTask2 extends RecursiveTask<Void> {
    private final Integer ADJUST = 1000 ;
    private Integer begin;
    private Integer end;
    private CopyOnWriteArrayList<Integer> list;

    public MyTask2(Integer begin, Integer end, CopyOnWriteArrayList<Integer> list){
        this.begin = begin;
        this.end = end;
        this.list = list;
    }
    @Override
    protected Void compute() {
        if(end - begin < ADJUST){
            // 任务长度适合， 执行任务
            for (int i = begin; i <= end; i++) {
                list.add(i);
            }
        }
        else {
            // 任务长度太长， 分成多个线程执行
            int mid = (end - begin) / 2 + begin;
            MyTask2 myTask1 = new MyTask2(begin, mid, list);
            MyTask2 myTask2 = new MyTask2(mid + 1, end, list);
            // 调用fork()方法也会执行compute()
            invokeAll(myTask1, myTask2);
//            myTask1.fork();
//            myTask2.fork();
//            myTask1.join();
//            myTask2.join();
        }
        return null;
    }
}
