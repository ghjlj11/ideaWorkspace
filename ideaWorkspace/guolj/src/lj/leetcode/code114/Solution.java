package lj.leetcode.code114;

/**
 * @author 86187
 */
public class Solution {
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null){
            if(cur.left != null){
                TreeNode right = cur.right;
                TreeNode tail = cur.left;
                while (tail.right != null){
                    tail = tail.right;
                }
                tail.right = right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}
