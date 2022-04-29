package lj.leetcode.code338;

/**
 * @author 86187
 */
public class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        int temp = 1;
        int road = 0;
        for(int i = 1; i <= n; i ++){
            if(i == temp){
                dp[i] = 1;
                road = temp;
                temp *= 2;
            }
            dp[i] = 1 + dp[i - road];
        }
        return dp;
    }
    public int[] count (int n){
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 1 ; i <= n ; i ++){
            int temp = dp[i / 2];
            if(i % 2 == 0){
                dp[i] = temp;
            }
            else {
                dp[i] = temp + 1;
            }
        }
        return dp;
    }
    public int[] bits(int n){
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int i = 1 ; i <= n ; i ++){
            //将i的最后一个一去除，结果再加一；
            dp[i] = dp[i & (i - 1)] + 1;
        }
        return dp;
    }
}
