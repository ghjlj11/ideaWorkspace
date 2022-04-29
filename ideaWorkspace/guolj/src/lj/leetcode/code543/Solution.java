package lj.leetcode.code543;

/**
 * @author 86187
 */
public class Solution {
    private int max;
    public int diameterOfBinaryTree(TreeNode root) {
        theLong(root);
        return max;
    }
    private int theLong(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = theLong(root.left);
        int right = theLong(root.right);

        int th = left + right;
        max = Math.max(max, th);
        return Math.max(left, right) + 1;
    }
}
