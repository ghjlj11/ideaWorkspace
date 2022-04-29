package com.ghj.producterAndConsumer;

/**
 * @author 86187
 */
public class TestIs {
    public static void main(String[] args) {
        Container container = new Container();
        Productor productor = new Productor(container);
        Consumer consumer  = new Consumer(container);

        new Thread(productor,"productor").start();
        new Thread(consumer,"consumer1").start();
        new Thread(consumer,"consumer2").start();
        new Thread(consumer,"consumer3").start();
    }
}
class Productor extends Thread{
    Container container ;
    public Productor(Container container){
        this.container = container;
    }
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            try {
                container.push(new Chicken(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Consumer implements Runnable{
    Container container ;

    public Consumer(Container container){
        this.container = container;
    }
    @Override
    public void run() {
        for(int i = 0 ; i < 30 ; i ++){
            try {
                container.pop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Chicken{
    int id;
    public Chicken(int id) {
        this.id = id;
    }
}
class Container{
    Chicken[] chicken = new Chicken[5];
    int count = 0;
    public synchronized void push(Chicken c) throws InterruptedException {
        while (count == chicken.length){
            this.wait();
        }
        chicken[count] = c ;
        System.out.println("生产了第：" + c.id + "只鸡");
        count ++;
        this.notifyAll();
    }
    public synchronized void pop() throws InterruptedException {
        while (count < 1){
            this.wait();
        }
        count -- ;
        Chicken c = chicken[count];
        System.out.println( Thread.currentThread().getName() + "消费了第：" + c.id + "只鸡");
        chicken[count] = null;
        this.notifyAll();
    }
}
