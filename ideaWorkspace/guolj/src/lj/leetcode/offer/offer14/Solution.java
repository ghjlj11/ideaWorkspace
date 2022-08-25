package lj.leetcode.offer.offer14;

/**
 * @author 86187
 */
public class Solution {
    public int cuttingRope(int n) {
        if(n < 5){
            if(n == 2){return 1;}
            if(n == 3){return 2;}
            if(n == 4){return 4;}
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 4;
        for(int i = 5; i <= n; i ++){
            if(i % 3 == 1){
                dp[i] = ( dp[i - 1] / 3 ) * 4;
            }
            else {
                dp[i] =( dp[i - 1] / 2 ) * 3;
            }
        }
        return dp[n];
    }
    public int max(int n){
        if(n <= 3){
            return n - 1;
        }
        int a = n / 3;
        int b = n % 3;
        if(b == 0){
            return (int)Math.pow(3, a);
        }
        if(b == 1){
            return (int)Math.pow(3, a - 1) * 4;
        }
        return (int)Math.pow(3, a) * 2;
    }
}
