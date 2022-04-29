package lj.leetcode.code647;

/**
 * @author 86187
 */
public class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[len][len];
        dp[0][0] = true;
        int sum = 1 ;
        for(int i = 1 ; i < len ; i ++){
            dp[i][i - 1] = true;
            dp[i][i] = true;
            sum ++;
        }
        for(int i = len - 1 ; i >= 0 ;i --){
            for(int j = i + 1 ; j < len ; j ++){
                if(chars[i] == chars[j] && dp[i + 1][j - 1]){
                    dp[i][j] = true;
                    sum ++;
                }
            }
        }
        return sum;
    }
}
