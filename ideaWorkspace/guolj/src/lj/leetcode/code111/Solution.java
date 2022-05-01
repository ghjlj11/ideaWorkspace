package lj.leetcode.code111;

import lj.leetcode.code189.Main;

/**
 * @author 86187
 */
class Solution {
    int minDep = 100000;
    int deep = 0;
    public int minDepth(TreeNode root) {
        if(root == null){
            return deep;
        }
        dfs(root);
        return minDep;
    }
    private void dfs(TreeNode root){
        if(root.right == null && root.left == null){
            minDep = Math.min(minDep, deep + 1);
        }
        else {
            if(root.left != null){
                deep ++;
                dfs(root.left);
                deep --;
            }
            if(root.right != null){
                deep ++;
                dfs(root.right);
                deep --;
            }
        }
    }
}
