package lj.leetcode.code283;

public class Solution {
    public void moveZeroes(int[] nums) {
    	int j = 0 ;
    	int len = nums.length;
    	for(int i = 0 ; i < len ; i ++) {
    		if(nums[i] != 0) {
    			nums[j++] = nums[i];
    		}
    	}
    	for( ; j < len ; j ++) {
    		nums[j] = 0;
    	}
    }
}
