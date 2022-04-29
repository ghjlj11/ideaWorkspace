package com.ghj.deadLock;

/**
 * @author 86187
 */
public class DeadLock01 {
    public static void main(String[] args) throws InterruptedException {
        Mirror mirror = new Mirror();
        Lipstick lipstick = new Lipstick();
        Girl girl1 = new Girl(mirror, lipstick, 1);
        Girl girl2 = new Girl(mirror, lipstick, 2);
         new Thread(girl1,"girl1").start();
         new Thread(girl2,"girl2").start();
    }
}

class Mirror{

}

class Lipstick{

}

class Girl extends Thread{

    private  Mirror mirror ;
    private  Lipstick lipstick ;

    private int choose;

    public Girl(Mirror mirror, Lipstick lipstick, int choose){
        this.choose = choose;
        this.lipstick = lipstick;
        this.mirror = mirror;
    }
    @Override
    public void run() {
        if(choose == 1){
            synchronized (mirror){
                System.out.println( Thread.currentThread().getName() + "获得了镜子的锁");
                synchronized (lipstick){
                    System.out.println( Thread.currentThread().getName() + "获得了口红的锁");
                }
            }
        }
        else {
            synchronized (lipstick){
                System.out.println( Thread.currentThread().getName() + "获得了口红的锁");
                synchronized (mirror){
                    System.out.println( Thread.currentThread().getName() + "获得了镜子的锁");
                }
            }
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}