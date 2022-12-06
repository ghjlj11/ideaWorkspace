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



## Lock版本的生产消费者

首先我们知道Lock可以直接替换synchronized， 然后wait()方法和notifyAll()方法也可以被Condition的await()方法与signal()方法代替。

使用官网的例子说明Condition

```java
/**
 * 官网的例子
 */
class BoundedBuffer {
   final Lock lock = new ReentrantLock();
   final Condition notFull  = lock.newCondition();
   final Condition notEmpty = lock.newCondition(); 

   final Object[] items = new Object[5];
   int putptr, takeptr, count;

   public void put(Object x) throws InterruptedException {
     lock.lock(); try {
       while (count == items.length)
         notFull.await();
       items[putptr] = x;
       if (++putptr == items.length) putptr = 0;
       ++count;
       System.out.println(Thread.currentThread().getName() + "存放了了第" + putptr + "个， 是====>" + x);
       notEmpty.signal();
     } finally { lock.unlock(); }
   }

   public Object take() throws InterruptedException {
     lock.lock(); try {
       while (count == 0)
         notEmpty.await();
       Object x = items[takeptr];
       if (++takeptr == items.length) takeptr = 0;
       --count;
       System.out.println(Thread.currentThread().getName() + "拿取了第" + count + "个， 是====>" + x);
       notFull.signal();
       return x;
     } finally { lock.unlock(); }
   }
 } 
```



Condition可以实现精准唤醒线程。

之前的例子可以换成

```java
class A{
    public int num = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    Condition condition1 = lock.newCondition();
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (num != 0){
                //相当于wait方法
                condition.await();
            }
            System.out.println(Thread.currentThread().getName() + "===>" + num);
            num ++;
            //相当于notify方法
            condition1.signal();//实现精准唤醒
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            while (num == 0){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "===>" + num);
            num --;
            condition.signal();//实现精准唤醒
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}

```



## 8锁现象



- 1 锁是锁住的调用者

```java
/**
 * @author 86187
 * 关于锁的问题
 * 方法上的锁是将调用该方法的对象锁住
 * 下面测试始终是发短信先获取锁， 因为中间主线程有睡眠1秒， 在发短信时候不管睡眠多久， 锁都是在发短信身上， 必须执行完发短信， 打电话才能获取锁
 */
public class Test01 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSm();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}
class Phone{
    public synchronized void sendSm(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
    public void sayHello(){
        System.out.println("hello");
    }
}

```



- 2 未被锁住的方法， 可以不用获取锁也可以执行

```java
/**
 * @author 86187
 * 关于锁的问题
 * 方法上的锁是将调用该方法的对象锁住
 * 当执行没有被锁的方法， 那么就不需要拿取到对象的锁， 先执行hello
 */
public class Test02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();
        new Thread(() -> {
            phone.sendSm();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.sayHello();
        }, "B").start();
    }
}
class Phone2{
    public synchronized void sendSm(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
    public void sayHello(){
        System.out.println("hello");
    }
}
```



- 3 不同的对象有不同的锁

```java
/**
 * @author 86187
 * 关于锁的问题
 * 方法上的锁是将调用该方法的对象锁住， 这里有两个对象， 因此phone1的锁被拿走了， phone2的锁一样可以获取， 因此先执行打电话， 再发短信
 * 先打电话， 再发短信
 */
public class Test03 {
    public static void main(String[] args) {
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();
        new Thread(() -> {
            phone1.sendSm();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}
class Phone3{
    public synchronized void sendSm(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }
}

```



- 4 带有static的方法是锁住类的class模板

```java
/**
 * @author 86187
 * 关于锁的问题
 * 先发短信， 再打电话
 */
public class Test04 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(() -> {
            phone1.sendSm();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}
class Phone4{
    //当这里加上了static，那就是锁的Class对象，每个类只有一个Class对象
    // Class<Phone4> aClass = Phone4.class;
    public static synchronized void sendSm(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public static synchronized void call(){
        System.out.println("打电话");
    }
}

```



- 5 锁住类的class模板， 类new出来的对象并不会被锁住

```java
/**
 * @author 86187
 * 关于锁的问题
 * 先发短信， 再打电话
 */
public class Test04 {
    public static void main(String[] args) {
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(() -> {
            phone1.sendSm();
        }, "A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}
class Phone4{
    //当这里加上了static，那就是锁的Class对象，每个类只有一个Class对象
    // Class<Phone4> aClass = Phone4.class;
    public static synchronized void sendSm(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public static synchronized void call(){
        System.out.println("打电话");
    }
}

```



## CopyOnWriteArrayList



- 写入时复制的ArrayList， 其中的add()方法， 先复制原先的数组， 然后add数据， 然后改变原先数组的Array的引用

```java
    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }
```



- 测试

```java
/**
 * @author 86187
 * ConcurrentModificationException并发修改异常
 */
public class TestList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //这里会直接抛出这个异常
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(String.valueOf(new Random().nextInt(100)));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
    @Test
    public void test01(){
        //Collections让List集合线程安全的方法 或者使用Vector也是线程安全的
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(String.valueOf(new Random().nextInt(100)));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    @Test
    public void test02(){
        //写入时复制的ArrayList， 线程安全
        //在多线程写入的时候， 会先复制一份，然后改变Array引用 ， 避免写入的数据被覆盖掉
        //CopyOnWriteArrayList适合用在'读多写少'的时候， 比Vector的效率更高， Vector都是用的synchronized， 这个用的是Lock
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(String.valueOf(new Random().nextInt(100)));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}

```



## CopyOnWriteArraySet



- 多线程下Set与List类似

```java
/**
 * 同理， Set和list类似
 * ConcurrentModificationException用HashSet一样抛这个异常
 */
public class TestSet {
    @Test
    public void test01(){
        //Set<String> set = new HashSet<>();
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                set.add(String.valueOf(new Random().nextInt(100)));
                System.out.println(Thread.currentThread().getName() +  "====>" + set);
            } , String.valueOf(i)).start();
        }
    }
    @Test
    public void test02(){
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                set.add(String.valueOf(new Random().nextInt(100)));
                System.out.println(Thread.currentThread().getName() +  "====>" + set);
            } , String.valueOf(i)).start();
        }
    }
}

```



## Callable



- Callable与Runnable不同的是， Callabe有返回值， 并且可以抛异常



我们观察Thread的构造方法， 可以看出里面没有关于Callable接口的构造， 只有一些Runnable以及线程组的构造， 想要让Callable跑起来还是需要Thread的构造， 因此在Runnable下有一个FutureTask类， 它的构造既可以用Runnable也可以用Callable， 因此这就把Callable和Runnable关联起来了， 也就和Thread关联起来了。



- 因此我们直接调用Callable的call()方法就相当于是利用FutureTask新开了一个线程

```java
public class TestCallAble {
    public static void main(String[] args) throws Exception {
        FutureTask futureTask = new FutureTask(new Call());
        new Thread(futureTask, "A").start();
        //这里并没有再次执行 ，应为会被缓存
        new Thread(futureTask, "B").start();
        //如果get方法是一个很耗时的操作那可能会产生阻塞
        Object o = futureTask.get();
        System.out.println(o);
        //等价于上面的
        new Call().call();
    }
}

class Call implements Callable{
    @Override
    public Object call() throws Exception {
        System.out.println("call()");
        return 333;
    }
}

```



## 常用辅助类



- 用作减1的计数器操作CountDownLatch

```java
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(8);
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                //让CountDownLatch里的值减1
                System.out.println(Thread.currentThread().getName() + "==>" + "xxxxxx");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        //当CountDownLatch里的值变为0才会继续走下去
        countDownLatch.await();
        System.out.println("haha");
    }
}

```



- CyclicBarrier是一个加法计数器， 当调用cyclicBarrier.await()就会加1， 达到指定的值就会执行指定的线程， 调用cyclicBarrier.await()也会让执行这条命令的线程阻塞

```java
/**
 * @author 86187
 * CyclicBarrier也是一个计数器， 当达到一定值时候就会执行一个线程
 * 这个值需要每次调用cyclicBarrier.await()就会加1， 调用同时也会让使用此方法的线程进入阻塞
 * 当达到这个规定的值的时候， 指定的线程会自动执行。
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7 , () -> {
            System.out.println("芝麻开门");
        });

        for (int i = 0; i < 7; i++) {
            final int temp = i;
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + "===>" + temp);
                try {
                    cyclicBarrier.await();
                    System.out.println("现在是===>" + temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}

```



结果：

```txt
1===>1
3===>3
2===>2
0===>0
5===>5
4===>4
6===>6
芝麻开门
现在是===>3
现在是===>2
现在是===>4
现在是===>1
现在是===>6
现在是===>5
现在是===>0

进程已结束,退出代码0

```



- Semaphore拿取与释放资源

```java
/**
 * @author 86187
 * Semaphore设置一个资源数， 线程可以去获取资源也可以释放资源， 如果资源全被获取， 那么需要拿取资源的线程就需要等待有线程释放资源
 * 可以用来限流
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    //拿取资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "==>获取到了资源");
                    //释放资源
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "==>释放了了资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}

```



## ReadWriteLock



读写锁是比Lock更细致的锁， 拥有读写两把锁， 写锁只可以一个线程拥有； 读锁可以共享， 但是不可以写

```java
/**
 * @author 86187
 * 读写锁
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Catch aCatch = new Catch();
        //写入操作
        for (int i = 0; i < 5; i++) {
            final int t = i;
            new Thread(() -> {
                aCatch.put(t + "", t + "");
            }, i + "").start();
        }
        //读取操作
        for (int i = 0; i < 5; i++) {
            final int t = i;
            new Thread(() -> {
                aCatch.get(t + "");
            }, i + "").start();
        }
    }
}
class Catch{
    private volatile Map<String, String> map = new HashMap<>();
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String k, String v){
        //写锁， 只可以有一个线程写
        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "==>进入到写操作");
        map.put(k, v);
        System.out.println(Thread.currentThread().getName() + "==>写操作  ok");
        readWriteLock.writeLock().unlock();
    }
    public void get(String k){
        //读锁， 可以多个线程读， 但是不可以写
        readWriteLock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "==>进入到读操作");
        map.get(k);
        System.out.println(Thread.currentThread().getName() + "==>读操作  ok");
        readWriteLock.readLock().unlock();
    }
}

```



## 阻塞队列

- 在Collection下的Queue下的BlockingQueue  ， 实现类：

```java
ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
```



常用的四组API：

| 方式         | 抛出异常  | 有返回值，不抛出异常 | 阻塞 等待 | 超时等待  |
| ------------ | --------- | -------------------- | --------- | --------- |
| **添加**     | add()     | offer()              | put()     | offer(,,) |
| **移除**     | remove()  | poll()               | take()    | poll(,)   |
| **队首元素** | element() | peek()               | -         | -         |



> 同步队列 SynchronousQueue



- 这个队列是BlockingQueue的子类， 特点就是里面只能存储一个元素， 存了之后必须取出来才可以继续存入。



## 线程池



Executors是一个工具类，可以为我们创建线程池， 有三种方式， 单一的， 指定数量的， 以及无限的依赖的系统的

```java
/**
 * @author 86187
 * Executors
 */
public class ExecutorDemo {
    public static void main(String[] args) {
        //创建一个单一的线程池
        ExecutorService threadPoll = Executors.newSingleThreadExecutor();
        //创建指定大小的线程池
        threadPoll = Executors.newFixedThreadPool(5);
        //无上限，系统能执行多少就多少
        threadPoll = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            threadPoll.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "===>" );
            });
        }
        threadPoll.shutdown();
    }
}
```

 



以上三中方式创建线程池都是调用了下面的方法：

```java
public ThreadPoolExecutor(int corePoolSize,//核心线程数
                              int maximumPoolSize,// 最大线程数
                              long keepAliveTime,// 最大存活时间，超时 就会释放该线程池
                              TimeUnit unit,// 上面时间的单位
                              BlockingQueue<Runnable> workQueue,// 阻塞队列， 等待区的位置
                              ThreadFactory threadFactory,// 线程工厂， 一般不改
                              RejectedExecutionHandler handler// 拒绝策略 4 种
                         ) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
```

 

**正常情况下， 我们只有核心线程在执行， 当请求的太多，就会到阻塞队列里边， 当阻塞队列也满了， 那么就会开启新的线程， 就是在最大线程里面调用， 当还是满了不够用， 就会采取拒绝策略**



```java
ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,
                5,
                3,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 0; i < 9; i++) {
                poolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "===>");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            poolExecutor.shutdown();
        }
```





> 拒绝策略

- 之前用Executors默认采用的是`new ThreadPoolExecutor.AbortPolicy()`，这种策略 会抛出异常
- `new ThreadPoolExecutor.CallerRunsPolicy`， 就是哪来的回哪去。
- `new ThreadPoolExecutor.DiscardOldestPolicy()`， 会尝试和第一个执行的线程竞争， 不会抛异常。
- `new ThreadPoolExecutor.DiscardPolicy`， 满了不会抛出异常



> 如何设置最大线程数

- CPU密集型：获取CPU的最大线程数， 然后设置最大线程数为这个

```java
//获取当前服务器的最大线程数
System.out.println(Runtime.getRuntime().availableProcessors());
```

- IO密集型：查看进行IO操作很耗时的线程数，  然后 设置大于这个数 的最大线程数，一般为两倍。 



## Stream



stream流是对集合的一种操作， 可以过滤， 筛选， 以及排序出一些数据

```java
/**
 * @author 86187
 * stream流式计算
 * filter 过滤， 参数为断言型函数接口
 * map 传入Stream泛型里的一个对象， 转化为一个任意的值
 * sorted 排序输出， 传入一个比较器
 * limit 指定输出前几个
 * 终止操作 只能有 一次！
 */
public class StreamDemo {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(new Student(11, "A", 12), new Student(22, "B", 13), new Student(33, "C", 14));
        Stream<Student> stream = list.stream();
        Stream<String> limit = stream.filter(n -> n.getAge() > 11)
                .map(n -> n.getName().toLowerCase())
                .sorted((o1, o2) -> o2.compareTo(o1))
                .limit(2);
        limit.forEach(System.out::println); //相当于终止了， 接下来就不能使用这个流了
        System.out.println(limit.count());
    }
}
```



## ForkJoin

将大任务拆分成多个小任务，由多个线程执行， 加快执行的速度

```java
import java.util.concurrent.RecursiveTask;

/**
 * @author 86187
 */
public class MyTask extends RecursiveTask<Integer> {
    private final Integer ADJUST = 10 ;
    private Integer begin;
    private Integer end;
    private Integer result = 0;

    public MyTask(Integer begin, Integer end){
        this.begin = begin;
        this.end = end;
    }
    @Override
    protected Integer compute() {
        if(end - begin < ADJUST){
            for (int i = begin; i <= end; i++) {
                result += i;
            }
        }
        else {
            int mid = (end - begin) / 2 + begin;
            MyTask myTask1 = new MyTask(begin, mid);
            MyTask myTask2 = new MyTask(mid + 1, end);
            // 调用fork()方法也会执行compute()
            myTask1.fork();
            myTask2.fork();
            result = myTask1.join() + myTask2.join();
        }
        return result;
    }
}

```



> 测试

```java
public class ForkJoinTaskTest {
    public static void main(String[] args) throws Exception {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // ForkJoinPool提交任务
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        System.out.println(submit.get());
        forkJoinPool.shutdown();
    }
}
```



> ForkJoin特点

工作窃取， 当一个线程执行完任务之后， 其他线程的任务还没有执行完， 那么该线程就会去执行别的线程未执行的任务（任务是放在一个双端队列里面， 窃取的是从底部开始执行任务， 正常线程是从顶部开始），以提高工作效率。



## JMM



**JMM规定了所有的变量都存储在主内存（Main Memory）中。每个线程还有自己的工作内存（Working  Memory）,线程的工作内存中保存了该线程使用到的变量的主内存的副本拷贝，线程对变量的所有操作（读取、赋值等）都必须在工作内存中进行，而不能直接读写主内存中的变量（volatile变量仍然有工作内存的拷贝，但是由于它特殊的操作顺序性规定，所以看起来如同直接在主内存中读写访问一般）。不同的线程之间也无法直接访问对方工作内存中的变量，线程之间值的传递都需要通过主内存来完成。**



![8cedf683cdfacb3cfcd970cd739d5b9d](img\juc\8cedf683cdfacb3cfcd970cd739d5b9d.jpg)



- 线程要获取主内存里的数据时， 都需要成对出现， 比如说Read之后就需要Write， Load之后就要Store， 否则就会导致线程阻塞。



- 代码探究， 因为new的线程只是进行Read操作， 没有Write， 因此没有获取主内存里的最新的值， 就会阻塞

```java
/**
 * @author 86187
 * 测试jmm， 线程会阻塞
 */
public class JmmDemo {
    public static int num = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (num == 0){

            }
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //此时虽然修改了num， 但是线程并没有再次去读主内存里的值， 因此会阻塞
        num = 1;
    }
}

```



> volatile



- 保证可见性： 当以上代码加上volatile关键字， 就会让num在各个线程里面可见， 也就不会一直运行下去。
- 不保证原子性：正常情况下也是很多时候不能到20000， 加了volatile也是不能保证的

```java
/**
 * @author 86187
 * volatile保证可见性， 不保证原子性，禁止指令重排
 * 
 */
public class VolatileDemo {
    public static volatile int num = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    num ++;
                }
            }).start();
        }

        //获取存活线程数量， java默认会有main，GC线程两个
        while (Thread.activeCount() > 2){
            Thread.yield();
        }

        //很多时候不是20000
        System.out.println(Thread.currentThread().getName() + "=>" + num);
    }
}

```



解决这个问题我们可以用锁， 也可以使用原子性的类， 这个比锁会更加高效， 直接在内存中执行加1。

```java
/**
 *使用juc下的atomic包下的AtomicInteger可以保证原子性
 */
public static volatile AtomicInteger num = new AtomicInteger(0);
```



- 禁止指令重排：我们正常写的代码， 计算机可能不会按照正常的顺序， 因为有的时候结果不受影响， 但是执行多线程的情况下， 指令重排就可能产生影响了。



## 单例模式



- DCL双重检查机制

```java
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

```



- 但是在反射下， 单例都是没用的， 因为反射可以获取私有的构造， 然后创建对象， 只有使用枚举，才可以避免这种问题， 枚举类里面看似是有一个空参数的私有构造函数， 但是使用反射获取时候， 其实不是空参数的， 会获取失败， 其实是有一个String和int类型参数的。 



## run()与start()



- start()
  用 start方法来启动线程，是真正实现了多线程， 通过调用Thread类的start()方法来启动一个线程，这时此线程处于就绪（可运行）状态，并没有运行，一旦得到cpu时间片，就开始执行run()方法。但要注意的是，此时无需等待run()方法执行完毕，即可继续执行下面的代码。所以run()方法并没有实现多线程。

- run()
  run()方法只是类的一个普通方法而已，如果直接调用Run方法，程序中依然只有主线程这一个线程，其程序执行路径还是只有一条，还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下面的代码。

- 区别
  1、线程中的start()方法和run()方法的主要区别在于，当程序调用start()方法，将会创建一个新线程去执行run()方法中的代码。但是如果直接调用run()方法的话，会直接在当前线程中执行run()中的代码，注意，这里不会创建新线程。这样run()就像一个普通方法一样。

​		2、另外当一个线程启动之后，不能重复调用start()，否则会报IllegalStateException异常。但是可以重复调		用run()方法。

​		总结起来就是run()就是一个普通的方法，而start()会创建一个新线程去执行run()的代码。



- 还有：
  1、start方法用来启动相应的线程；

​		2、run方法只是thread的一个普通方法，在主线程里执行；

​		3、需要并行处理的代码放在run方法中，start方法启动线程后自动调用run方法；

​		4、run方法必去是public的访问权限，返回类型为void。

​		如果直接调用线程类的run()方法，这会被当做一个普通的函数调用，程序中仍然只有主线程这一个线程，也		就是说，start()方法能够异步地调用run()方法，但是直接调用run()方法却是同步的，因此也就无法达到多线		程的目的。
