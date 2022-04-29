package lj.leetcode.code63;

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid[0][0]==1){
            return 0;
        }
        int n=obstacleGrid.length,m=obstacleGrid[0].length;
        int[][] dp=new int[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(i==0&&j==0){
                    dp[i][j]=1;
                }
                else {
                    if(obstacleGrid[i][j]==0){
                        int a=i>0?dp[i-1][j]:0;
                        int b=j>0?dp[i][j-1]:0;
                        dp[i][j]=a+b;
                    }
                    else {
                        dp[i][j]=0;
                    }
                }
            }
        }
        return dp[n-1][m-1];
    }
    public int dp(int[][] obstacleGrid){
        if(obstacleGrid[0][0]==1){
            return 0;
        }
        int length=obstacleGrid[0].length;
        int[] dp2=new int[length];
        dp2[0]=1;
        int[] dp1=dp2;
        for(int j=0;j<obstacleGrid.length;j++){
            dp2[0]=dp1[0];
            if(obstacleGrid[j][0]==1){
                dp2[0]=0;
            }
            for(int k=1;k<length;k++){
                if(obstacleGrid[j][k]!=1){
                    dp2[k]=dp2[k-1]+dp1[k];
                }
                else {
                    dp2[k]=0;
                }
            }
            dp1=dp2;
        }
        return dp2[length-1];
    }
}
class Main {
    public static void main(String[] args){
        int[][] n={{0,0,0},{0,1,0},{0,0,0}};
        Solution A=new Solution();
        System.out.println(A.dp(n));
    }
}
