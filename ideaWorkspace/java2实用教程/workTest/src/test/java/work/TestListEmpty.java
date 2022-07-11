package work;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 86187
 */

public class TestListEmpty {
    @Test
    public void t1(){
        List<Integer> list = new ArrayList<>();
        //false
        System.out.println(list == null);
        list.add(1);
        list.remove(0);
        //true
        System.out.println(list.isEmpty());
        //false
        System.out.println(list == null);
    }
}