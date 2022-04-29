package lj.leetcode.code100;

/**
 * @author 86187
 */
public class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return dfs(p, q);
    }
    private boolean dfs(TreeNode p, TreeNode q){
        if(p == null || q == null){
            return p==q;
        }
        else {
            if(dfs(p.left, q.left) && dfs(p.right, q.right)){
                if(p.val == q.val){
                    return true;
                }
                else {
                    return false;
                }
            }
            return false;
        }
    }
}
