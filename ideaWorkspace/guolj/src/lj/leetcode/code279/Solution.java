package lj.leetcode.code279;

/**
 * @author 86187
 */
public class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        int cur = 1;
        int next = cur + 1;
        for(int i = 1; i < n + 1; i ++){
            if(i >= next * next){
                cur ++;
                next ++;
            }
            int temp = dp[i - 1] + 1;
            for(int j = cur; j > 0; j --){
                temp = Math.min(temp, 1 + dp[i - j * j]);
            }
            dp[i] = temp;
        }
        return dp[n];
    }
    public int num(int n){
        if(isOne(n)){
            return 1;
        }
        if(isFour(n)){
            return 4;
        }
        for(int i = 1; i * i <= n; i ++){
            if(isOne(n - i * i)){
                return 2;
            }
        }
        return 3;
    }
    private boolean isOne(int n){
        int k = (int)Math.sqrt(n);
        return k * k == n;
    }
    private boolean isFour(int n){
        while (n % 4 == 0){
            n /= 4;
        }
        return n % 8 == 7;
    }
}
