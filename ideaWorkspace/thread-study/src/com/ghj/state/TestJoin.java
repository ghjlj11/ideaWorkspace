package com.ghj.state;

/**
 * @author 86187
 */
public class TestJoin implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        for(int i = 0 ; i < 100 ; i ++){
            if(i == 80){
                thread.start();
                thread.join();
            }
            System.out.println("main" + i);
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("插队了" + i);
        }
    }
}
