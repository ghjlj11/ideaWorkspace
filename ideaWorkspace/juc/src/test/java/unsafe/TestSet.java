package unsafe;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

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
