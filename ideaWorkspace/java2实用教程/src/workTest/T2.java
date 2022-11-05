package workTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 86187
 */
public class T2 {
    String x;
    int c ;
    Lock lock = new ReentrantLock();
    public void t2(){
        lock.lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            System.out.println(name + "start");
            System.out.println(name + "---------------");
            System.out.println(name + "end");
            System.out.println(name + "---------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
