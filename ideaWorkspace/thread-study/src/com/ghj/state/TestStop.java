package com.ghj.state;

/**
 * @author 86187
 */
public class TestStop implements Runnable{
    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (flag){
            System.out.println("Thread running..." + i ++);
        }
    }
    private  void stop(){
        this.flag = false;
    }
    public static void main(String[] args){
        TestStop testStop = new TestStop();
        Thread thread = new Thread(testStop);
        thread.start();
        for(int i = 0 ; i < 1000 ; i ++){
            System.out.println("main running..." + i);
            if(i == 800){
                testStop.stop();
                System.out.println("线程结束了!");
            }
        }
    }
}
