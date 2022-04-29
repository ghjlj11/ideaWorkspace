package com.ghj.state;

/**
 * @author 86187
 */
public class TestYield implements Runnable{
    @Override
    public void run() {
        String s = Thread.currentThread().getName();
        System.out.println( s + "线程开始");
        if("a".equals(s)){
            Thread.yield();
        }
        System.out.println(s + "线程结束");
    }

    public static void main(String[] args){
        TestYield t = new TestYield();
        new  Thread(t,"a").start();
        new  Thread(t,"b").start();
        new  Thread(t,"c").start();
    }
}
