package lj.leetcode.code538;

/**
 * @author 86187
 */
public class Solution {
    public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }
    private int dfs(TreeNode root , int sum){
        if(root == null){
            return sum;
        }
        sum = dfs(root.right, sum);
        root.val = root.val + sum;
        sum = dfs(root.left, root.val);
        return sum;
    }
}
