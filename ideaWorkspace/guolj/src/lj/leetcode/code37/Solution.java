package lj.leetcode.code37;

import java.util.*;

public class Solution {
    public void solveSudoku(char[][] board) {
        boolean[][] hang=new boolean[9][9];
        boolean[][] lie=new boolean[9][9];
        boolean[][][] xie=new boolean[3][3][9];
        for(int m=0;m<9;m++){
            Arrays.fill(hang[m],true);
            Arrays.fill(lie[m],true);
        }
        for(int m=0;m<3;m++){
            for(int n=0;n<3;n++){
                Arrays.fill(xie[m][n],true);
            }
        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                if(board[i][j]!='.'){
                    int x=i/3,y=j/3;
                    int k=board[i][j]-'1';
                    hang[i][k]=false;
                    lie[j][k]=false;
                    xie[x][y][k]=false;
                }
            }
        }
        pp(hang,lie,xie,board,0,0);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.println(board[i][j]);
            }
        }
    }
    private boolean pp(boolean[][] hang,boolean[][] lie,boolean[][][] xie,char[][] board,int row,int ii){

        if(ii==9){
            ii=0;
            row++;
            if(row==9){
                return true;
            }
        }
        if(board[row][ii]=='.'){
            int x=row/3,y=ii/3;
            for(int i=0;i<9;i++){
                if(!(hang[row][i]&&lie[ii][i]&&xie[x][y][i])){continue;}
                board[row][ii]=(char)(i+'1');
                hang[row][i]=false;
                lie[ii][i]=false;
                xie[x][y][i]=false;
                if(pp(hang,lie,xie,board,row,ii+1)){
                    return true;
                }
                board[row][ii]='.';
                hang[row][i]=true;
                lie[ii][i]=true;
                xie[x][y][i]=true;
            }
        } else{
            return pp(hang,lie,xie,board,row,ii+1);
        }
        return false;
    }
}
