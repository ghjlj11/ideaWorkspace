package lj.leetcode.code101;

/**
 * @author 86187
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        return yes(root.left, root.right);
    }
    private boolean yes(TreeNode left , TreeNode right){
        if(left == null || right == null){
            return left == null && right == null;
        }
        if(left.val == right.val){
            return yes(left.left, right.right) && yes(left.right, right.left);
        }
        return false;
    }
}
