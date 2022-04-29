package lj.leetcode.code621;

import java.util.List;

/**
 * @author 86187
 */
public class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] dp = new int[26];
        for (char task : tasks) {
            dp[task - 'A'] ++;
        }
        int maxCount = 0 ;
        for (int i : dp) {
            maxCount = Math.max(maxCount, i);
        }
        int x = 0;
        for (int i : dp) {
            if(i == maxCount){
                x ++;
            }
        }
        return Math.max((n + 1) * ( maxCount - 1 ) + x, tasks.length);
    }
}
