package lj.leetcode.code6075;

import java.util.Arrays;

class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int len = capacity.length;
        int[] x = new int[len];
        for (int i = 0; i < len; i++) {
            x[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(x);
        int sum = 0 ;
        for(int i = 0 ; i < len ; i ++){
            if((additionalRocks -= x[i]) >= 0){
                sum ++;
            }
            else {
                break;
            }
        }
        return sum;
    }
}
