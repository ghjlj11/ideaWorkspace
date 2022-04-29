package lj.leetcode.code406;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author 86187
 */
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Comparator<int[]> comparator = (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1];
        Arrays.sort(people, comparator);
        int len = people.length;
        int[][] res = new int[len][];
        for (int[] person : people) {
            int k = person[1] + 1;
            for(int i = 0 ; i < len ; i ++){
                if(res[i] == null){
                    k --;
                }
                if(k == 0){
                    res[i] = person;
                    break;
                }
            }
        }
        return res;
    }
}
