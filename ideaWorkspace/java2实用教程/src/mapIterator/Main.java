package mapIterator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();

        map.put(1,2);
        map.put(2,3);
        map.put(3,4);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry);
        }

        System.out.println("-------------------------------");
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator();

        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> entry = iterator.next();
            System.out.println(entry);
        }

        System.out.println("----------只对values迭代---------");

        for (Integer value : map.values()) {
            System.out.println(value);
        }

    }
}
