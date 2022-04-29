package lj.leetcode.code322;

import java.util.Arrays;

/**
 * @author 86187
 */
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int min = 0;
        int len = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp,-1);
        dp[0] = 0;
        for(int i = 0; i < len; i ++){
            for(int j = 1 ; j <= amount ; j ++){
                int x = -1;
                if(j >= coins[i]){
                    x = dp[j - coins[i]];
                }
                if( x > -1){
                    if(dp[j] != -1){
                        dp[j] = Math.min(x + 1, dp[j]);
                    }
                    else {
                        dp[j] = x + 1;
                    }
                }
            }
        }
        return dp[amount];
    }
    public int coinC(int[] coins, int amount){
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        dfs(coins, amount, dp);
        return dp[amount];
    }
    private int dfs(int[] coins, int amount, int[] dp){
        if(amount == 0){
            return dp[0];
        }
        if(dp[amount] != 0){
            return dp[amount];
        }
        int min = 10000;
        for(int coin : coins){
            if(amount >= coin){
                int res = dfs(coins, amount - coin, dp);
                if( res >= 0 && res < min){
                    min = 1 + res;
                }
            }
        }
        dp[amount] = (min == 10000) ? -1 : min;
        return dp[amount];
    }
}
