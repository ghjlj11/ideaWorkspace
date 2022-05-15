package lj.leetcode.code6064;

import java.util.Arrays;

class Solution {
    public int maxConsecutive(int bottom, int top, int[] special) {
        int res = 0;
        int len = special.length, p = special[0];
        int[] max = new int[len];
        for(int i = 0 ; i < len ; i ++){

        }
        for(int i = 1 ; i < len ; i ++){
            int k = special[i] - p - 1;
            res = Math.max(res, k);
            p = special[i];
        }
        return Math.max(res, top - p);
    }
}
