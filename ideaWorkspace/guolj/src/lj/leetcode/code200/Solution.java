package lj.leetcode.code200;

/**
 * @author 86187
 */
public class Solution {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] dp = new boolean[n][m];
        int sum = 0;
        for(int i = 0; i < n ; i ++){
            for(int j = 0 ; j < m ; j ++){
                if(grid[i][j] == '1'){
                    check( grid, n, m ,i, j);
                    sum ++;
                }
            }
        }
        return sum;
    }
    private void isChecked(boolean[][] dp, char[][] grid, int n, int m,int pos1, int pos2){
        dp[pos1][pos2] = true;
        if(pos1 - 1 >= 0 && grid[pos1 - 1][pos2] == '1'&& !dp[pos1 - 1][pos2]){
            isChecked(dp, grid, n, m, pos1 - 1, pos2);
        }
        if(pos1 + 1 < n && grid[pos1 + 1][pos2] == '1' && !dp[pos1 + 1][pos2]){
            isChecked(dp, grid, n, m, pos1 + 1, pos2);
        }
        if(pos2 + 1 < m && grid[pos1][pos2 + 1] == '1' && !dp[pos1][pos2 + 1]){
            isChecked(dp, grid, n, m , pos1, pos2 + 1);
        }
        if(pos2 - 1 >= 0 && grid[pos1][pos2 - 1] == '1' && !dp[pos1][pos2 - 1]){
            isChecked(dp, grid, n, m , pos1, pos2 - 1);
        }
    }
    private void check(char[][] grid, int n, int m, int pos1, int pos2){
        if(pos1 >= n  || pos1 < 0 || pos2 >= m || pos2 < 0 || grid[pos1][pos2] != '1'){
            return;
        }
        grid[pos1][pos2] = '5';
        check(grid, n, m, pos1 + 1, pos2);
        check(grid, n, m, pos1 - 1, pos2);
        check(grid, n, m, pos1, pos2 + 1);
        check(grid, n, m, pos1, pos2 - 1);
    }
}
