package com.ghj.demo01;

/**
 * @author 86187
 */
public class TestThread01 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("run----");
        }
    }
    public static void main(String[] args){
        TestThread01 testThread = new TestThread01();
        testThread.start();
        for (int i = 0; i < 200; i++) {
            System.out.println("main-----");
        }
    }
}
