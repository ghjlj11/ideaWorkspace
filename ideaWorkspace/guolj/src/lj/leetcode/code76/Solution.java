package lj.leetcode.code76;

import java.util.*;

/**
 * @author 86187
 */
class Solution {
    public String minWindow(String s, String t) {
        char[] sc = s.toCharArray();
        int scl = sc.length;
        char[] tc = t.toCharArray();
        int tcl = tc.length;
        int[] flag = {-1, scl};
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tcl; i++) {
            map.put(tc[i], map.getOrDefault(tc[i], 0) + 1);
        }
        List<Integer> arr = new ArrayList<>();
        int k = map.size();
        for (int i = 0; i < scl; i++) {
            if(map.containsKey(sc[i])){
                arr.add(i);
            }
        }
        if(arr.size() < tcl){
            return "";
        }
        Deque<Integer> dp = new LinkedList<>();
        int size = arr.size();
        for(int i = 0 ; i < size ; i ++){
            int x = arr.get(i);
            dp.add(x);
            int y = map.get(sc[x]);
            map.put(sc[x], y - 1);
            while (map.get(sc[dp.peek()]) < 0){
                int peek = dp.pop();
                map.put(sc[peek], map.get(sc[peek]) + 1);
            }
            if(y == 1){
                k --;
            }
            if(k == 0){
                int first = dp.getFirst(), last = dp.getLast();
                if(flag[1] - flag[0] > last - first){
                    flag[1] = last;
                    flag[0] = first;
                }
            }
        }
        if(flag[0] == -1){
            return "";
        }
        return s.substring(flag[0], flag[1] + 1);
    }
}
