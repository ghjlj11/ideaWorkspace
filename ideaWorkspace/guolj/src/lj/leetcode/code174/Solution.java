package lj.leetcode.code174;

/**
 * @author 86187
 */
public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n][m];
        dp[n - 1][m - 1] = Math.max(1, 1 - dungeon[n - 1][m - 1]);
        for(int i = n - 2; i >= 0; i --){
            int min = dp[i + 1][m - 1] - dungeon[i][m - 1];
            dp[i][m - 1] = Math.max(1, min);
        }
        for(int i = m - 2; i >= 0 ; i --){
            int min = dp[n - 1][i + 1] - dungeon[n - 1][i];
            dp[n - 1][i] = Math.max(1, min);
        }
        for(int  i = n - 2 ; i >= 0; i --){
            for(int j = m - 2 ; j >= 0 ; j --){
                int min = Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j];
                dp[i][j] = Math.max(min,1);
            }
        }
        return dp[0][0];
    }
    public int help(int[][] dungeon){
        int n = dungeon.length;
        int m = dungeon[0].length;
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i <= m ; i ++){
            dp[n][i] = Integer.MAX_VALUE;
        }
        for(int i = 0 ; i <= n; i ++){
            dp[i][m] = Integer.MAX_VALUE;
        }
        dp[n - 1][m - 1] = Math.max(1, 1 - dungeon[n - 1][m - 1]);
        minLoad(n, m, 0, 0, dungeon, dp);
        return dp[0][0];
    }
    private void minLoad(int n, int m, int row, int col, int[][] dungeon,int[][] dp){
        if(row >= n  || col >= m || dp[row][col] != 0){
            return;
        }
        if(row == n - 1 && col == m - 1){
            return;
        }
        minLoad(n , m, row , col + 1, dungeon, dp);
        minLoad(n , m , row + 1, col, dungeon, dp);
        int min = Math.min(dp[row + 1][col], dp[row][col + 1]) - dungeon[row][col];
        dp[row][col] = Math.max(1, min);
    }
}
