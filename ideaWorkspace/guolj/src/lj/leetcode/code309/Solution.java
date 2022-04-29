package lj.leetcode.code309;

import java.util.Arrays;

/**
 * @author 86187
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if(len == 1){
            return 0;
        }
        int[] max = new int[3];
        max[0] = -prices[0];
        for(int i = 1; i < len; i ++){
            int a = max[0];
            int b = max[1];
            int c = max[2];
            max[0] = Math.max(a, c - prices[i]);
            max[1] = a + prices[i];
            max[2] = Math.max(c, b);
        }
        return Math.max(max[1], max[2]);
    }
}
