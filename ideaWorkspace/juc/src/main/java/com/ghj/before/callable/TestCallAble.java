package com.ghj.before.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author 86187
 */
public class TestCallAble {
    public static void main(String[] args) throws Exception {
        FutureTask futureTask = new FutureTask(new Call());
        new Thread(futureTask, "A").start();
        //这里并没有再次执行 ，应为会被缓存
        new Thread(futureTask, "B").start();
        //如果get方法是一个很耗时的操作那可能会产生阻塞
        Object o = futureTask.get();
        System.out.println(o);
        //等价于上面的
        new Call().call();
    }
}

class Call implements Callable{
    @Override
    public Object call() throws Exception {
        System.out.println("call()");
        return 333;
    }
}
