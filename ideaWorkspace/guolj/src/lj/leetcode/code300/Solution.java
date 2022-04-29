package lj.leetcode.code300;

import java.util.Arrays;

/**
 * @author 86187
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int res = 1 ;
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        for(int i = 1 ; i < len ; i ++){
            int j = i - 1 ;
            while (j >= 0){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[j] , dp[i]);
                }
                j --;
            }
            dp[i]++;
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
