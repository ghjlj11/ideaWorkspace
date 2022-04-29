package lj.inclass;

import lj.leetcode.code84.Solution;

import java.util.*;

/**
 * @author 86187
 */
public class Sb {
    public static void main(String[] args){
        Integer[] n = {1,3,-1,-3,5,3,6,7};

        Arrays.sort(n, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });
        HashMap<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.getKey();
        }
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        int i = random.nextInt(0, 2);
        System.out.println(i);
        String l = "1,2,3,4,,,5,6,,7,8,,,";
        String[] split = l.split(",");
        System.out.println(Arrays.toString(split));
        System.out.println(split.length);
        List<Object> k = new ArrayList<>();
        k.add(null);
        k.add(null);
        k.add(null);
        System.out.println(k);
    }
}
