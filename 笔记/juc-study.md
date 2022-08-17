# juc-study





## 进程与线程

1. 线程是程序执行的最小单位，而进程是操作系统分配资源的最小单位；

2. 一个进程由一个或多个线程组成，线程是一个进程中代码的不同执行路线

3. 进程之间相互独立，但同一进程下的各个线程之间共享程序的内存空间(包括代码段，数据集，堆等)及一些进程级的资源(如打开文件和信号等)，某进程内的线程在其他进程不可见；

4. 调度和切换：线程上下文切换比进程上下文切换要快得多
4. java默认开了两个线程， main， GC。



> 并发与并行

- 并发：在CPU只有1核的情况下， 模拟多线程， 那么CPU就快速交替执行这些线程， 并不是同时执行
- 并行：在CPU多核的情况下， 就可以同时执行多个线程。



> 查看CPU的核数

- 通过任务管理器==> 性能==> CPU ==> 逻辑处理器 ， 可以查看到
- 通过java代码， 结果为8 。

```java
//查看CPU的核数
System.out.println(Runtime.getRuntime().availableProcessors());
```



并发编程的本质就是 ：**从分利用CPU的资源**



> 进程与线程的状态

进程的五种状态：创建， 就绪，运行， 阻塞， 结束。

线程有六个状态：`java.lang.Thread`类里的`State`内部类

```java
public enum State {
    //新建状态
        NEW,
	//运行状态
        RUNNABLE,
	//阻塞状态
        BLOCKED,
	//等待状态
        WAITING,
	//定时等待
        TIMED_WAITING,
	//终止状态
        TERMINATED;
    }
```



> wait与sleep

- sleep是Thread类的方法， wait是Object的方法
- sleep需要捕获异常， wait不需要。
- sleep会一直占用锁， 而wait不会，并且进入到等待池， 不进行锁的争抢
- sleep可以在任何地方执行， wait需要在同步代码块里面。

 

## synchronized

**修饰在方法与代码块上**



卖票的例子：

```java
package com.ghj.synchronnized;
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

```





## lock



> ReentrantLock是一个非公平锁

```java
    /**
     * Creates an instance of {@code ReentrantLock}.
     * This is equivalent to using {@code ReentrantLock(false)}.
     */
    public ReentrantLock() {
        sync = new NonfairSync();//new 一个非公平锁
    }
	/**
     * Creates an instance of {@code ReentrantLock} with the
     * given fairness policy.
     *
     * @param fair {@code true} if this lock should use a fair ordering policy
     */
    public ReentrantLock(boolean fair) {//通过 fair来判断生成公平锁 还是非公平锁
        sync = fair ? new FairSync() : new NonfairSync();
    }

```



公平锁：**就是可以实现线程先来先使用资源**

非公平锁：**就是不能实现线程先来先使用资源**， 这样其实可以提升效率， 如果一个线程执行需要1小时， 另一个需要1秒

> 使用lock

```java
/**
 * @author 86187
 * 使用lock的方法， 先new一个lock， 选择实现类， 然后在需要上锁的代码前面加上lock.lock()方法，然后把正常的代码放在try里面
 * 在finally使用lock.unlock解锁
 */
public class SaleTicket2 {
    public static void main(String[] args) {
    Tickets2 tickets = new Tickets2();
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
class Tickets2 {
    private int num = 30;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if(num > 0){
                System.out.println("num===>" + num--);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            lock.unlock();
        }
    }
}

```



## synchronized与lock区别

1、synchronized是关键字， Lock是一个类。

2、synchronized不可以查看锁的状态， Lock可以查看是否获取到锁。

3、synchronized自动释放锁， Lock需要手动释放unLock()方法。

4、synchronized锁会让为获得锁的线程一直等下去， Lock不一定， tryLock()方法。

5、synchronized 可重入锁，非公平锁，不可以中断， Lock可重入锁， 非公平（可以设置）；

6、synchronized一般锁少量代码， Lock一般锁大量代码。



## 生产者消费者模型

```java
/**
 * @author 86187
 * 生产者与消费者
 */
public class Test1 {
    public static void main(String[] args) {
        A a = new A();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    a.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    a.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    a.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    a.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
class A{
    public int num = 0;
    public synchronized void increment() throws InterruptedException {
        while (num != 0){//这里需要用while，防止判断一次后等待， 然后别的线程操作了，此时条件不满足，但依旧会继续wang'xia
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "===>" + num);
        num ++;
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        while (num == 0){
            this.wait();
        }
        System.out.println(Thread.currentThread().getName() + "===>" + num);
        num --;
        this.notifyAll();
    }
}

```

