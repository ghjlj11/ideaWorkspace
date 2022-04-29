package lj.leetcode.code226;

/**
 * @author 86187
 */
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null){
            return null;
        }
        TreeNode k = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(k);
        return root;
    }
}
