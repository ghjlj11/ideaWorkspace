package lj.leetcode.code113;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
class Solution {
    List<List<Integer>> res;
    int sum;
    int targetSum;
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        this.res = new ArrayList<>();
        if(root == null){
            return res;
        }
        this.targetSum = targetSum;
        this.sum = 0;
        dfs(root, new ArrayList<>());
        return res;
    }
    private void dfs(TreeNode root, List<Integer> temp){
        temp.add(root.val);
        sum += root.val;
        if(root.left != null || root.right != null){
            if(root.left != null){
                dfs(root.left, temp);
            }
            if(root.right != null){
                dfs(root.right, temp);
            }
        }
        else if(sum == targetSum){
            res.add(new ArrayList<>(temp));
        }
        sum -= root.val;;
        temp.remove(temp.size() - 1);
    }
}
