package unsafe;






import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

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
