package lj.leetcode.code79;

import java.util.HashMap;

/**
 * @author ghj
 */
public class Main {
    public static void main(String[] args){
    }
}
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==words[0]){
                    if(dfs(board,words,i,j,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] border,char[] words,int row,int col,int length){
        if(length == words.length){
            return true;
        }
        if(col < 0 || col >= border[0].length || row < 0 || row >= border.length || (border[row][col] != words[length])){
            return false;
        }
        border[row][col] += 200;
        boolean res = dfs(border, words, row + 1, col, length + 1) || dfs(border, words, row -1, col, length + 1)
                || dfs(border, words, row, col - 1,length + 1) || dfs(border, words, row, col + 1, length + 1);
        border[row][col] -=200;
        return res;
    }
}