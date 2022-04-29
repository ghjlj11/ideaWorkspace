package my_generics;

import java.util.*;

/**
 * @author 86187
 */
public class Test01 {
    public static void main(String[] args){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List list1 = list;
        list1.add("3");
//        List<Integer> list2 = null;
//        list2.add(3);
//        list2.add(4);
        List<Integer> s2 = list1;
        System.out.println(Objects.equals(list, list1));
        System.out.println(s2.get(1).getClass());
        System.out.println(list1.get(2).getClass());
        phone<Integer> p = new phone<>(123);
        System.out.println(p.getT().getClass());
        Map<Integer, String> map = new HashMap<>();
        System.out.println();
    }
}
