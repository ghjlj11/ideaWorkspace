package lj.leetcode.code64;

public class Main {
    public static void main(String[] args){
        int[][] grid={{1,2,3},{4,5,6}};
        Solution A=new Solution();
        System.out.println(A.minPathSum(grid));
    }
}
class Solution {
    public int minPathSum(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[] dp2=new int[m];
        dp2[0]=grid[0][0];
        for(int i=1;i<m;i++){
            dp2[i]=grid[0][i]+dp2[i-1];
        }
        int[] dp1=dp2;
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                int a=dp1[j];
                int b=j>0?dp2[j-1]:Integer.MAX_VALUE;
                dp2[j]=grid[i][j]+Math.min(a,b);
            }
            dp1=dp2;
        }
        return dp2[m-1];
    }
}
