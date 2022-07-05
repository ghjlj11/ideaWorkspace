package myTest.MyBase;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

/**
 * @author 86187
 */
public class MyComparable {
    public static void main(String[] args) {
        int x = 9;
        Queue<Integer> q = new ArrayDeque<>();
        Iterator<Integer> iterator = q.iterator();
        String s = "123456";
        String v = s.intern();
        v = new String(v);
        System.out.println(s == v);
        System.out.println(v);
    }
}
