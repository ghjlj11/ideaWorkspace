package com.ghj.after.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author guohuanjun1
 * @date 2023/9/19 22:50
 */
public class MyCallable implements Callable<String> {

    private int sleepTime;

    public MyCallable(int sleepTime){
        this.sleepTime = sleepTime;
    }

    @Override
    public String call() throws Exception {
        System.out.println("haha callable");
        TimeUnit.SECONDS.sleep(this.sleepTime);
        return "haha";
    }
}
