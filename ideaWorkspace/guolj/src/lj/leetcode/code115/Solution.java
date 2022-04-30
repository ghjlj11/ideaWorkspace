package lj.leetcode.code115;

/**
 * @author 86187
 */
class Solution {
    private int sum = 0;
    private char[] sc;
    private char[] tc;
    public int numDistinct(String s, String t) {
        sc = s.toCharArray();
        tc = t.toCharArray();
        if(sc.length < tc.length){
            return 0;
        }
        int[][] dp = new int[tc.length][sc.length];
        int tSum = 0;
        for(int i = sc.length - 1 ; i >= 0 ;i --){
            if(sc[i] == tc[tc.length - 1]){
                tSum ++;
            }
            dp[tc.length - 1][i] = tSum;
        }
        int k = 0;
        for(int i = tc.length - 1 ; i >= 0 ; i --, k++ ){
            if(sc[sc.length - 1] == tc[i]){
                dp[i][sc.length - 1 - k] = 1;
            }
        }
        k = 0;
        for(int i = tc.length - 2 ; i >=0 ; i --, k ++){
            tSum = dp[i][sc.length - 1 - k];
            for(int j = sc.length - 2 - k ; j >=0 ; j --){
                if(sc[j] == tc[i]){
                    tSum += dp[i + 1][j + 1];
                }
                dp[i][j] = tSum;
            }
        }
        return dp[0][0];
    }
}
