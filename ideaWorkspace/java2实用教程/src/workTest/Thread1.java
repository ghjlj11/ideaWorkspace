package workTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 86187
 */
public class Thread1 extends Thread {
    private String name;
    private T2 t2;

    public Thread1(String name, T2 t2) {
        this.name = name;
        this.t2 = t2;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(this.name);
        t2.t2();
    }
}
