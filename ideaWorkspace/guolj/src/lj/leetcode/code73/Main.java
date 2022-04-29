package lj.leetcode.code73;

public class Main {
    public static void main(String[] args){}
}
class Solution {
    public void setZeroes(int[][] matrix) {
        int n=matrix.length,m=matrix[0].length;
        boolean[] rows=new boolean[n];
        boolean[] cols=new boolean[m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(matrix[i][j]==0){
                    rows[i]=true;
                    cols[j]=true;
                }
            }
        }
        for(int i=0;i<n;i++){
            if(rows[i]){
                for(int j=0;j<m;j++){
                    matrix[i][j]=0;
                }
            }
        }
        for(int i=0;i<m;i++){
            if(cols[i]){
                for(int j=0;j<n;j++){
                    matrix[j][i]=0;
                }
            }
        }
    }
    public void Zeroes(int[][] matrix){
        int n = matrix.length , m = matrix[0].length;
        boolean row = false , col = false;
        for(int i = 0;i < n;i++){
            if(matrix[i][0] == 0){
                col = true;
                break;
            }
        }
        for(int i = 0;i < m;i++){
            if(matrix[0][i] == 0){
                row = true;
                break;
            }
        }
        for(int i = 1;i < n;i++){
            for(int j = 1;j < m;j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for(int i = 1;i < n;i++){
            if(matrix[i][0] == 0){
                for(int j = 1;j < m;j++){
                    matrix[i][j] = 0;
                }
            }
        }
        for(int i = 1;i < m;i++){
            if(matrix[0][i] == 0){
                for(int j = 1;j < n;j++){
                    matrix[j][i] = 0;
                }
            }
        }
        if(row){
            for(int i = 0;i < m;i++){
                matrix[0][i] = 0;
            }
        }
        if(col){
            for(int i = 0;i < n;i++){
                matrix[i][0] = 0;
            }
        }
    }
}
