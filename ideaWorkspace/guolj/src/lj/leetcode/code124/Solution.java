package lj.leetcode.code124;

/**
 * @author 86187
 */
public class Solution {
    int max = Integer.MIN_VALUE ;
    public int maxPathSum(TreeNode root) {
        maxSum(root);
        return max;
    }
    private int maxSum(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftMax = maxSum(root.left);
        int rightMax = maxSum(root.right);
        int theMax = root.val;
        if(leftMax > 0){
            theMax += leftMax;
        }
        if(rightMax > 0){
            theMax += rightMax;
        }
        max = Math.max(max, theMax);
        return Math.max(leftMax + root.val, Math.max(rightMax + root.val , root.val));
    }
}
