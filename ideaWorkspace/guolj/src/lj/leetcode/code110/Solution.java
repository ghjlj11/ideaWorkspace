package lj.leetcode.code110;

/**
 * @author 86187
 */
class Solution {
    boolean flag = true;
    public boolean isBalanced(TreeNode root) {
        deep(root);
        return flag;
    }
    private int deep(TreeNode root){
        if(root == null){
            return 0;
        }
        int l = deep(root.left);
        int r = deep(root.right);
        if(Math.abs(l - r) > 1){
            flag = false;
        }
        return  1 + Math.max(l , r);
    }
}
