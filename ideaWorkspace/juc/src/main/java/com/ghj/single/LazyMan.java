package com.ghj.single;

/**
 * @author 86187
 * 懒汉式枚举的多线程案例  DCL
 */
public class LazyMan {
    private LazyMan(){
        System.out.println(Thread.currentThread().getName() + "==>  ok" );
    }

    /**
     * volatile防止指令重排， 当A线程获取锁， 然后在new 对象时候， 因为new 是一个过程， 先要分配空间， 然后创建对象， 对象指向该地址
     * 当指令重排的时候， 直接分配空间， 然后指向该地址， 那么B线程会判断 单例不为空， 但是返回时候还是为空
     */
    private volatile static LazyMan lazyMan;
    public static LazyMan getInstance(){
        if(lazyMan == null){
            //双重检验
            synchronized (lazyMan){
                if(lazyMan == null){
                    lazyMan = new LazyMan();
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            LazyMan instance1 = getInstance();
        }).start();
        LazyMan instance = getInstance();
    }
}
