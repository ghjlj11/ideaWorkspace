package lj.leetcode.code287;

public class Solution {
    public int findDuplicate(int[] nums) {
    	int flow = 0, fast = 0,res = 0 ;
    	while(true) {
    		flow = nums[flow];
    		fast = nums[nums[fast]];
    		if(flow == fast) {
    			int temp = 0 ;
    			while(temp != flow) {
    				res = temp;
    				temp = nums[temp];
    				flow = nums[flow];
    			}
    			break;
    		}
    	}
    	return nums[res];
    }
}
