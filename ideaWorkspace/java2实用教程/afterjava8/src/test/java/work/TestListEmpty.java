package work;

import org.junit.Test;

import java.util.*;

/**
 * @author 86187
 */

public class TestListEmpty {
    @Test
    public void t1(){
        Integer[] l = new Integer[]{3,2,1,4,8,9,6,5,7};
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1 + "---->" + o2);
                return o2 - o1;
            }
        };
        Arrays.sort(l, comparator);
        System.out.println(Arrays.toString(l));
    }
}