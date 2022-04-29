package lj.leetcode.code99;

/**
 * @author 86187
 */
public class Solution {
    TreeNode t1 , t2 , next;
    public void recoverTree(TreeNode root) {
        dfs(root);
        if( t1 != null && t2 != null){
            int temp = t1.val;
            t1.val = t2.val;
            t2.val = temp;
        }
    }
    private void dfs(TreeNode root ){
        if(root == null){
            return;
        }
        dfs(root.left);
        if(next != null && next.val > root.val){
            if(t1 == null){
                t1 = next;
            }
            t2 = root;
        }
        next = root;
        dfs(root.right);
    }
}
