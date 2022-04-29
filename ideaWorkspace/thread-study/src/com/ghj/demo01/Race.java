package com.ghj.demo01;

/**
 * @author 86187
 */
public class Race implements Runnable{
    private static String win ;
    @Override
    public void run() {
        for(int i = 0 ; i <= 100 ; i ++){
            if(!isRun(i)){
                break;
            }
            if(Thread.currentThread().getName().equals("兔子") && i%9 == 0){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "跑了" + i + "步");
        }
    }

    private boolean isRun(int i){
        if(win != null){
            return false;
        }
        else if(i == 100 ){
            win = Thread.currentThread().getName();
            System.out.println(win + "赢了");
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
