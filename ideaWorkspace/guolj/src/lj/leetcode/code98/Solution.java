package lj.leetcode.code98;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
public class Solution {
    public boolean isValidBST(TreeNode root) {
        long[] var = {Long.MIN_VALUE};
        return dfs(var, root);
    }
    private boolean dfs(long[]  var,  TreeNode root){
        if(root == null){
            return true;
        }
        boolean ans = dfs(var, root.left);
        if(var[0] < root.val){
            var[0] = root.val;
        }
        else {
            return false;
        }
        if(ans){
            ans = dfs(var, root.right);
        }
        return ans;
    }
}
