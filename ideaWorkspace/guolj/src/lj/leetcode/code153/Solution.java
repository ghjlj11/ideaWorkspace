package lj.leetcode.code153;

class Solution {
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1, t = nums[r];
        while (l < r){
            int mid = (l + r) / 2;
            if(nums[mid] < t){
                r = mid ;
            }
            else {
                l = mid + 1;
            }
        }
        return nums[l];
    }
}
