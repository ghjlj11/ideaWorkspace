package lj.leetcode.code122;

/**
 * @author 86187
 */
class Main {
    public static void main(String[] args){
        int[] n = {1,7,2,5};
        Solution A = new Solution();
        System.out.println(A.max(n));
    }
}
public class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        int  dp2 = 0;
        for(int i = 1; i < length ; i++){
            dp2 = dp2 + Math.max(0,prices[i] - prices[i-1]);
        }
        return dp2;
    }
    public int max(int[] prices){
        int length = prices.length;
        int[] dp = new int[length];
        dp[0] = 0;
        int min = prices[0];
        for(int i = 1; i < length; i ++){
            int temp = Math.max(0,prices[i] - min);
            dp[i] = dp[i - 1] + temp;
            min = prices[i];
        }
        return dp[length - 1];
    }
}
