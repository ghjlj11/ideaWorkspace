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

 
