package lj.leetcode.code162;

class Solution {
    boolean flag = false;
    int res = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Solution a = new Solution();
        int[] nums = {1,2,1,3,5,6,4};
        System.out.println(a.findPeakElement(nums));
    }
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if(len == 1 || nums[0] > nums[1]){
            return 0;
        }
        else if(nums[len - 1] > nums[len - 2]){
            return len - 1;
        }
        find(nums, 0, len - 1 );
        return res;
    }
    private void find(int[] nums, int star, int end){
        if(flag || star == end - 1){
            return;
        }
        int k = end/2 - star/2 + star;
        if(nums[k] > nums[k - 1] && nums[k] > nums[k + 1]){
            flag = true;
            res = k;
        }
        find(nums, star, k);
        find(nums, k , end);
    }
}
