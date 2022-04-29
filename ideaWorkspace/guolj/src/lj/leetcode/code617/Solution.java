package lj.leetcode.code617;

/**
 * @author 86187
 */
public class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2){
        boolean r1 = root1 == null;
        boolean r2 = root2 == null;
        TreeNode res ;
        if(r1 && r2){
            return null;
        }
        else if(r1){
            res = new TreeNode(root2.val);
        }
        else if(r2){
            res = new TreeNode(root1.val);
        }
        else {
            res = new TreeNode(root1.val + root2.val);
        }

        res.left = mergeTrees(r1 ? null : root1.left, r2 ? null : root2.left);

        res.right = mergeTrees(r1 ? null : root1.right, r2 ? null : root2.right);
        return res;
    }
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2){
        if(root1 == null){
            return root2;
        }
        if(root2 == null){
            return root1;
        }
        TreeNode res = new TreeNode(root1.val + root2.val);
        res.left = mergeTrees2(root1.left, root2.left);
        res.right = mergeTrees2(root1.right, root2.right);
        return res;
    }
}
