package lj.leetcode.code139;
import java.util.ArrayList;
import java.util.List;
/**
 * @author 86187
 */
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        for(int i = 1 ; i <= len ; i ++){
            for(int j = 0; j < i; j ++){
                if(dp[j] && wordDict.contains(s.substring(j , i ))){
                    dp[i] = true;
                }
            }
        }
        return dp[len];
    }
    public boolean word(String s, List<String> wordDict){
        int length = s.length();
        boolean[] dp = new boolean[length];
        if(wordDict.contains(s.substring(0,1))){
            dp[0] = true;
        }
        dfs(1, wordDict, s, length, dp);
        return dp[length - 1];
    }
    private void dfs(int star, List<String> wordDict, String s,int length, boolean[] dp){
        if(star == length){
            return;
        }
        for(int i = 0; i <= star ; i ++){
            String temp = s.substring(i, star + 1);
            if(wordDict.contains(temp)){
                if(i > 0 && dp[i - 1]){
                    dp[star] = true;
                }
                if(i == 0){
                    dp[star] = true;
                }
            }
        }
        dfs(star + 1, wordDict, s, length, dp);
    }
}