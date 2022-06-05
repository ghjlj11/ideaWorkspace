package myTest.myJVM;

import java.util.Random;

/**
 * @author 86187
 */
public class MaxMemory {
    public static void main(String[] args) {
        long l = Runtime.getRuntime().maxMemory();
        System.out.println(l / 1024 / 1024);
        long l1 = Runtime.getRuntime().totalMemory();
        System.out.println(l1 / 1024 / 1024);
        String s = new String("qewrwt");
        while (true){
            s += s + new Random().nextInt(888888888) + new Random().nextInt(999999999);
        }
    }
}
