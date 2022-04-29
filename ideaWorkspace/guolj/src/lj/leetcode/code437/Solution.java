package lj.leetcode.code437;

/**
 * @author 86187
 */
public class Solution {
    int res = 0;
    public int pathSum(TreeNode root, int targetSum) {
        if(root == null){
            return 0 ;
        }
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        dfs(root, 0, targetSum);
        return res;
    }
    private void dfs(TreeNode root, int sum, int target){
        if(root == null){
            return ;
        }
        sum += root.val;
        if(sum == target){
            res ++;
        }
        dfs(root.left, sum, target);
        dfs(root.right, sum, target);
    }
}
