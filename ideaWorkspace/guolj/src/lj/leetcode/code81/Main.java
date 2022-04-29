package lj.leetcode.code81;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){
        int[] nums = {4,5,6,6,7,2,2,3,4,4};
        int t = 5;
        Solution a = new Solution();
        System.out.println(a.search(nums,t));
    }
}
class Solution {
    public boolean search(int[] nums, int target) {
        int length = nums.length;
        int left = 0, right = length - 1, mid ;
        while(left <= right){
            mid = (left + right)/2;
            if (nums[mid] == target) {
                return true;
            }
            else if(nums[mid] == nums[left] || nums[mid] == nums[right]){
                if(nums[mid] == nums[left]){
                    left++;
                }
                if(nums[mid] == nums[right]){
                    right--;
                }
            }
            else if(nums[mid] > nums[0] ){
                if(nums[mid] > target && target >= nums[0]){
                    right = mid - 1;
                }
                else if (nums[mid] < target || target < nums[0]){
                    left = mid + 1;
                }
            }
            else{
                if(nums[mid] < target && target <= nums[nums.length - 1]){
                    left = mid + 1;
                }
                else if(nums[mid] > target || target > nums[nums.length - 1]){
                    right = mid - 1;
                }
            }
        }
        return false;
    }
}
