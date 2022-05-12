package lj.leetcode.code130;

/**
 * @author 86187
 */
class Solution {
    boolean[][] dp;
    int len1 = 0 ;
    int len2  = 0;
    public void solve(char[][] board) {
        len1 = board.length;
        len2 = board[0].length;
        dp = new boolean[len1][len2];
        for(int i = 0  ; i < len2 ; i ++){
            dfs(board, 0, i);
            dfs(board, len1 - 1, i);
        }
        for(int i = 1 ; i < len1 - 1 ; i ++){
            dfs(board, i, 0);
            dfs(board, i, len2 - 1);
        }
        for(int i = 0 ; i < len1 ; i ++){
            for (int j = 0 ; j < len2 ; j ++){
                if(!dp[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }
    private void dfs(char[][] b, int x , int y){
        if(x < 0 || x >= len1 || y < 0 || y >= len2  || b[x][y] != 'O' || dp[x][y]){
            return;
        }
        dp[x][y] = true;
        dfs(b, x + 1, y);
        dfs(b, x - 1, y);
        dfs(b, x, y + 1);
        dfs(b, x, y - 1);
    }
}
