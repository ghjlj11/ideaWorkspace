package com.ghj.forkjointask;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author 86187
 */
public class TestForkJoinTask02 {
    public static void main(String[] args) throws Exception {
        long l = System.currentTimeMillis();
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        ForkJoinTask<Void> myTask = new MyTask2(0, 100000, list);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // ForkJoinPool提交任务
        forkJoinPool.invoke(myTask);
        //System.out.println(list);
        //System.out.println("size:" + list.size());
        forkJoinPool.shutdown();
        long l1 = System.currentTimeMillis() - l;
        System.out.println("多线程时间：" + l1 + "ms");

        long l2 = System.currentTimeMillis();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i <= 100000; i++) {
            list1.add(i);
        }
        long l3 = System.currentTimeMillis() - l2;
        System.out.println("单线程时间：" + l3 + "ms");
    }
}
