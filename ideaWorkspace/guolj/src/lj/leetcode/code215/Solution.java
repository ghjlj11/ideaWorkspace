package lj.leetcode.code215;

/**
 * @author 86187
 */
public class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        int target = len - k;
        return sort(nums, target, 0, len - 1);
    }
    private int sort(int[] nums, int target ,  int star, int end){
        int l = star;
        int r = end;
        while (l < r){
            while (l < r){
                if(nums[l] > nums[r]){
                    int k = nums[l];
                    nums[l] = nums[r];
                    nums[r] = k;
                    break;
                }
                r--;
            }
            while (l < r){
                if(nums[l] > nums[r]){
                    int k = nums[l];
                    nums[l] = nums[r];
                    nums[r] = k;
                    break;
                }
                l ++;
            }
        }
        int res;
        if(l < target){
            res = sort(nums, target, l + 1, end);
        }
        else if(l > target){
            res = sort(nums, target, star, l - 1);
        }
        else {
            res = nums[l];
        }
        return res;
    }
}
