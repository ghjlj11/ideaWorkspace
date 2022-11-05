package workTest;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 86187
 */
public class TestThread {
    private Lock lock = new ReentrantLock();
    private void he(){
        lock.lock();
        try{
            String name = Thread.currentThread().getName();
            System.out.println(name + "hello");
        } catch (Exception e){
            System.out.println(e);
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        T2 t2 = new T2();
        new Thread1("qqq", t2).start();
        new Thread1("www", t2).start();
        new Thread1("eee", t2).start();
//        TestThread testThread = new TestThread();
//        new Thread(testThread::he, "qqq").start();
//        new Thread(testThread::he, "www").start();
//        new Thread(testThread::he, "eee").start();
    }
}
