package lj.leetcode.code97;

/**
 * @author 86187
 */
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int s1Length = s1.length();
        int s2Length = s2.length();
        int s3Length = s3.length();
        if(s1Length + s2Length != s3Length){
            return false;
        }
        boolean[][] dp = new boolean[s1Length + 1][s2Length + 1];
        dp[0][0] = true;
        for( int i = 1; i <= s1Length; i ++){
            if(s1.charAt(i - 1) == s3.charAt(i - 1)){
                dp[i][0] = true;
            }
            else {
                break;
            }
        }
        for(int i = 1 ; i <= s2Length; i ++){
            if(s2.charAt(i - 1) == s3.charAt(i - 1)){
                dp[0][i] = true;
            }
            else{
                break;
            }
        }
        for(int i = 1; i < s1Length; i ++) {
            for(int j = 1; j < s2Length; j ++){
                dp[i][j] = ( dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) )
                        || ( dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1) );
            }
        }
        return dp[s1Length][s2Length];
    }
    public boolean sd(String s1, String s2, String s3){
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if(len1 + len2 != len3){
            return false;
        }
        boolean[][] dp = new boolean[len1][len2];
        return dfs(s1, s2, s3, dp, 0, 0, 0);
    }
    private boolean dfs(String s1, String s2, String s3,boolean[][] dp,int len1,int len2, int len3){
        if(len3 == s3.length()){
            return true;
        }
        if(len1 < s1.length() && len2 < s2.length() && dp[len1][len2] ){
            return false;
        }
        if(len1 < s1.length() && s1.charAt(len1) == s3.charAt(len3)){
            if(dfs(s1, s2, s3, dp, len1 + 1, len2, len3 + 1)){
                return true;
            }
        }
        if(len2 < s2.length() && s2.charAt(len2) == s3.charAt(len3) ){
            if(dfs(s1, s2, s3, dp, len1, len2 + 1, len3 + 1)){
                return true;
            }
        }
        if(len1 < s1.length() && len2 < s2.length()){
            dp[len1][len2] = true;
        }
        return false;
    }
}
