package lj.leetcode.code121;

/**
 * @author 86187
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int min = prices[0];
        int max = 0;
        for(int i = 1; i < length; i ++){
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}
