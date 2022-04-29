package com.ghj.state;

/**
 * @author 86187
 * 虚拟机关闭需要一点时间，所以在用户线程结束后还会有一点时间在执行god。
 */
public class TestDaemon {

    public static void main(String[] args) {
        God god = new God();
        You you = new You();
        Thread thread = new Thread(god);
        thread.setDaemon(true);
        new Thread(you).start();
        thread.start();
    }

}

class God implements Runnable{

    @Override
    public void run() {
        while (true){
            System.out.println("上帝守护着你");
        }
    }
}

class You extends Thread{
    @Override
    public void run(){
        for(int i = 0 ; i < 3000 ; i ++){
            System.out.println("你开心的活着");
        }
        System.out.println("you are die");
    }
}
