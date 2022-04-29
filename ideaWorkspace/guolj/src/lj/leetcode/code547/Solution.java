package lj.leetcode.code547;

import java.util.Set;

/**
 * @author 86187
 */
public class Solution {
    private boolean[] dp ;
    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;
        dp = new boolean[len];
        int sum = 0;
        for(int i = 0 ; i < len ; i ++){
            if(!dp[i]){
                sum ++;
                dp[i] = true;
                connected(isConnected, i);
            }
        }
        return sum;
    }
    private void connected(int[][] isConnected, int line){
        int len = isConnected[0].length;
        for(int i = 0 ; i < len ; i ++){
            if(isConnected[line][i] == 1 && !dp[i]){
                dp[i] = true;
                connected(isConnected, i);
            }
        }
    }
}
