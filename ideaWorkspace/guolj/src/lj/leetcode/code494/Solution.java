package lj.leetcode.code494;

/**
 * @author 86187
 */
public class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, target, 0, nums.length, 0, 0);
    }
    private int dfs(int[] nums, int target, int num, int length, int sum, int times){
        if(num == length){
            if(sum == target){
                return times + 1;
            }
            return times;
        }
        times = dfs(nums, target, num + 1, length, sum + nums[num], times);
        times = dfs(nums, target, num + 1, length, sum - nums[num], times);
        return times;
    }
    public int find (int[] nums, int target){
        int len = nums.length;
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        if( (sum - target) % 2 != 0 || (sum - target) < 0){
            return 0;
        }
        int neg = (sum - target) / 2;
        int[][] dp = new int[len + 1][neg + 1];
        for(int i = 1 ; i < neg + 1; i ++){
            dp[0][i] = 0;
        }
        for(int i = 0; i < len + 1; i ++){
            dp[i][0] = 1;
        }
        for(int i = 1; i < len + 1; i ++){
            for(int j = 0; j < neg + 1; j ++){
                if(nums[i - 1] > j){
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][neg];
    }
    public int findMax(int[] nums, int target){
        int len = nums.length;
        int sum = 0;
        for( int n : nums){
            sum += n;
        }
        int d = sum - target;
        if(d < 0 || d % 2 != 0){
            return 0;
        }
        int neg = d / 2;
        int[] dp1 = new int[neg + 1];
        int[] dp2 = new int[neg + 1];
        dp1[0] = 1;
        dp2[0] = 1;
        for(int i = 1; i < len + 1; i++){
            if(i % 2 == 1) {
                for (int j = 0; j < neg + 1; j++) {
                    dp2[j] = dp1[j];
                    if (nums[i - 1] <= j) {
                        dp2[j] += dp1[j - nums[i - 1]];
                    }
                }
            }
            else{
                for (int j = 0; j < neg + 1; j++) {
                    dp1[j] = dp2[j];
                    if (nums[i - 1] <= j) {
                        dp1[j] += dp2[j - nums[i - 1]];
                    }
                }
            }
        }
        if(len % 2 == 1){
            return dp2[neg];
        }
        else {
            return dp1[neg];
        }
    }
}
