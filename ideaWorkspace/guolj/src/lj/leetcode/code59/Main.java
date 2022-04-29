package lj.leetcode.code59;

public class Main {
    public static void main(String[] args){
        int n=4;
        Solution A=new Solution();
        int[][] s=A.generateMatrix(n);
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.println(s[i][j]);
            }
        }
    }
}
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res=new int[n][n];
        int k=1;
        for(int i=0;i<n/2;i++){
            for(int j=i;j<n-i-1;j++){
                res[i][j]=k;
                k++;
            }
            for(int j=i;j<n-i-1;j++){
                res[j][n-i-1]=k;
                k++;
            }
            for(int j=n-i-1;j>i;j--){
                res[n-i-1][j]=k;
                k++;
            }
            for(int j=n-i-1;j>i;j--){
                res[j][i]=k;
                k++;
            }
        }
        if(k==(n*n)){
            res[n/2][n/2]=k;
        }
        return res;
    }
}
