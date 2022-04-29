package lj.leetcode.code62;
import java.util.*;
public class Main {
    public static void main(String[] args){
        int m=3,n=3;
        Solution A=new Solution();
        System.out.println(A.dp(m,n));
    }
}
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp=new int[m][n];
        for(int i=0;i<m;i++){
            dp[i][0]=1;
        }
        for(int i=0;i<n;i++){
            dp[0][i]=1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dp[i][j]=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
    public int dp(int n,int m){
        int[] dp1=new int[m];
        int[] dp2=new int[m];
        Arrays.fill(dp1,1);
        for(int i=0;i<n-1;i++){
            dp2[0]=1;
            for(int j=1;j<m;j++){
                dp2[j]=dp1[j]+dp2[j-1];
            }
            dp1=dp2;
        }
        return dp1[m-1];
    }
}
