package lj.leetcode.code238;

import java.util.Arrays;

public class Solution {
    public int[] productExceptSelf(int[] nums) {
    	int n = nums.length;
    	int[] res = new int[n];  
    	int sum = 1;
    	for(int i = 0 ; i < n ; i ++) {
    		sum *= nums[i];
    		res[i] = sum;
    	}
    	sum = 1;
    	for(int i = n - 1 ; i > 0 ; i --) {
    		res[i] = res[i - 1] * sum;
    		sum *= nums[i];
    	}
    	res[0] = sum;
    	return res;
    }
}
