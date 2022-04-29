package lj.leetcode.code10;

/**
 * @author 86187
 */
public class Solution {
    public boolean isMatch(String s, String p) {
        int n = p.length();
        int m = s.length();
        char[] ch1 = s.toCharArray();
        char[] ch2 = p.toCharArray();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        //初始化，可能有*可以匹配0个字符；
        for(int i = 1; i <= n ; i ++){
            if(ch2[i - 1] == '*'){
                dp[i][0] = dp[i - 2][0];
            }
        }
        for(int i = 1; i <= n ; i ++){
            for(int j = 1 ; j <= m ; j ++){
                //当第i个为*时
                if(ch2[i - 1] == '*'){
                    //*匹配0个或1个前面的那个字符
                    if(dp[i - 1][j] || ( i >= 2 && dp[i - 2][j] ) ){
                        dp[i][j] = true;
                    }
                    //匹配多个字符， *前一个字符与s的当前的字符相同，则判断当前的i个能否和j-1个匹配，可以的话就*在匹配一个当前字符
                    //如果*前面是一个.则只须判断i与j-1是否匹配
                    else if(i >= 2 && dp[i ][j - 1]){
                        if(ch2[i - 2] == ch1[j - 1] || ch2[i - 2] == '.'){
                            dp[i][j] = true;
                        }
                    }
                }
                //如果第i个是.
                else if(ch2[i - 1] == '.'){
                    dp[i][j] = dp[i - 1][j - 1];
                }
                //如果第i个是英文字母
                else {
                    dp[i][j] = dp[i - 1][j - 1] && ch1[j - 1] == ch2[i - 1];
                }
            }
        }
        return dp[n][m];
    }
}
