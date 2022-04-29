package lj.leetcode.code198;

public class Rob198 {
    public static void main(String[] args){}
}
class Solution {
    public int rob(int[] nums) {
        int length=nums.length;
        if(length==1){
            return nums[0];
        }
        int[] dp=new int[length];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[1],nums[0]);
        for(int i=2;i<length;i++){
            dp[i]= Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[length-1];
    }
}
