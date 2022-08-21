package unsafe;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {
    @Test
    public void test01(){
        //抛出ConcurrentModificationException
        //Map<String, String> map = new HashMap<>();
        //安全
        Map<String , String> map = Collections.synchronizedMap(new HashMap<>());
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName() ,String.valueOf(new Random().nextInt(100)));
                System.out.println(Thread.currentThread().getName() +  "====>" + map);
            } , String.valueOf(i)).start();
        }
    }

    @Test
    public void test02(){
        //线程安全
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName() ,String.valueOf(new Random().nextInt(100)));
                System.out.println(Thread.currentThread().getName() +  "====>" + map);
            } , String.valueOf(i)).start();
        }
    }
}
