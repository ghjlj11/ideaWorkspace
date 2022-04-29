package lj.leetcode.code146;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){
        LRUCache d = new LRUCache(2);
        d.put(1,1);
        d.put(2,2);
        System.out.println(d.get(1));
        d.put(3,3);
        System.out.println(d.get(2));
        d.put(4,4);
        System.out.println(d.get(1));
        System.out.println(d.get(3));
        System.out.println(d.get(4));
    }
}
