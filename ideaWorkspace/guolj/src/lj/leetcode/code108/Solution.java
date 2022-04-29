package lj.leetcode.code108;

import java.util.Arrays;

/**
 * @author 86187
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        int len = nums.length;
        return creat(nums, 0, len - 1);
    }
    private TreeNode creat(int[] nums, int star, int end ){
        if(star > end){
            return null;
        }
        int mid = star + ( end - star ) / 2;
        TreeNode res = new TreeNode(nums[mid]);
        res.left = creat(nums, star, mid - 1);
        res.right = creat(nums, mid + 1, end);
        return res;
    }
}