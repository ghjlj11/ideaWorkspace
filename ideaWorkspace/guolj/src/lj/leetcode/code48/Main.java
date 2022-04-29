package lj.leetcode.code48;

public class Main {
    public static void main(String[] args){
        int[][] n={{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        Solution S=new Solution();
        S.ff(n);
        int m=n.length;
        for(int i=0;i<m;i++){
            for(int j=0;j<m;j++){
                System.out.println(n[i][j]);
            }
        }
    }
}
class Solution {
    public void rotate(int[][] matrix) {
        int n=matrix.length;
        for(int i=0;i<n/2;i++){
            int[] k=new int[n-i-1];
            for(int x=i;x<n-i-1;x++){
                k[x]=matrix[i][x];
            }
            for(int j=i;j<n-i-1;j++){
                matrix[i][j]=matrix[n-j-1][i];
                matrix[n-j-1][i]=matrix[n-i-1][n-j-1];
                matrix[n-i-1][n-j-1]=matrix[j][n-i-1];
                matrix[j][n-i-1]=k[j];
            }
        }
    }
    public void ff(int[][] matrix){
        int n=matrix.length;
        int[][] k=new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                k[j][n-i-1]=matrix[i][j];
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=k[i][j];
            }
        }
    }
}
