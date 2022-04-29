package lj.leetcode.code560;

/**
 * @author 86187
 */
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int sum = 0 ;
        int[] dp = new int[len];
        for(int i = 0 ; i < len ; i ++){
            for(int j = 0 ; j <= i ; j ++){
                dp[j] += nums[i];
                if(dp[j] == k){
                    sum ++;
                }
            }
        }
        return sum;
    }
}
