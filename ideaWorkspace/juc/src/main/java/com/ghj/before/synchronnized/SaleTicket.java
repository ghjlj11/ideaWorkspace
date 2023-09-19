package com.ghj.before.synchronnized;
import java.lang.Thread;
/**
 * @author 86187
 * 当sale方法不使用synchronized修饰时， 那么打印的数字顺序会乱也就是说线程没有按照指定的顺序执行
 * 当加上synchronized时， 那就是排队拿取锁资源。
 */
public class SaleTicket{
    public static void main(String[] args) {
        Tickets tickets = new Tickets();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                tickets.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                tickets.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                tickets.sale();
            }
        }, "C").start();
    }
}
class Tickets {
    private int num = 30;
    public synchronized void sale(){
        if(num > 0){
            System.out.println("num===>" + num--);
        }
    }
}
