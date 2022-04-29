package lj.leetcode.code416;

import java.util.Arrays;

/**
 * @author 86187
 */
public class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0,target = 0;
        for (int num : nums) {
            sum += num;
        }
        if(sum % 2 != 0){
            return false;
        }
        target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        boolean[] dp1 = new boolean[target + 1];
        int max = 0;
        dp[0] = true;
        dp1[0] = true;
        for (int i = 0 ; i < nums.length ; i ++) {
            int num = nums[i];
            int j = 1;
            if(i % 2 == 0){
                for (j = 0; j <= max; j++) {
                    if (dp[j]) {
                        dp1[j] = true;
                        if(j + num <= target){
                            dp1[j + num] = true;
                        }
                    }
                }
                System.out.println(Arrays.toString(dp1));
                max = Math.min(j - 1 + num, target);
                if (dp1[target]) {
                    return true;
                }
            }
            else {
                for (j = 0; j <= max; j++) {
                    if (dp1[j]) {
                        dp[j] = true;
                        if(j + num <= target){
                            dp[j + num] = true;
                        }
                    }
                }
                System.out.println(Arrays.toString(dp));
                max = Math.min(j - 1 + num, target);
                if (dp[target]) {
                    return true;
                }
            }
        }
        return false;
    }
}
