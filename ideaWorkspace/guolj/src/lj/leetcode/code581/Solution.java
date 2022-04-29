package lj.leetcode.code581;

/**
 * @author 86187
 */
public class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int len = nums.length;
        int min = nums[len - 1];
        int max = nums[0];
        int star = 0 ;
        int end = -1 ;
        for(int i = 0 ; i < len ; i ++){
            if(nums[i] < max){
                end = i;
            }
            max = Math.max(max, nums[i]);
        }
        for(int i = len - 1 ; i >= 0 ; i --){
            if(nums[i] > min){
                star = i;
            }
            min = Math.min(min, nums[i]);
        }
        return end - star + 1;
    }
}
