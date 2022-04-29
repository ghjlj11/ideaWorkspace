package lj.leetcode.code347;

import java.util.Arrays;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) {
        int[] n = {3,2,2,1,1,1};
        Solution A = new Solution();
        System.out.println(Arrays.toString(A.topKFrequent(n, 3)));
    }
}
