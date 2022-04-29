package lj.leetcode.code36;
public class Solution {
    public boolean isValidSudoku(char[][] board) {
        int i,j;
        for(i=0;i<9;i++){
            for(j=0;j<9;j++){
                if(board[i][j]=='.'){continue;}
                else{
                    for(int m=8;m>j;m--){
                        if(board[i][m]==board[i][j]){System.out.println(11);return false;}
                    }
                    for(int k=8;k>i;k--){
                        if(board[k][j]==board[i][j]){System.out.println(22);return false;}
                    }
                    int k=i/3,m=j/3;
                    for(int o=k*3;o<k*3+3;o++){
                        if(o==i){continue;}
                        for(int p=m*3;p<m*3+3;p++){
                            if(p==j){continue;}
                            if(board[o][p]==board[i][j]){System.out.println(33);return false;}
                        }
                    }
                }
            }
        }
        return true;
    }
}
