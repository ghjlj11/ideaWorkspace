package lj.leetcode.code337;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 */
public class Solution {
    Map<TreeNode, Integer> select = new HashMap<>();
    Map<TreeNode, Integer> noSelect = new HashMap<>();
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(select.getOrDefault(root, 0), noSelect.getOrDefault(root, 0));
    }
    private void dfs(TreeNode root){
        if(root == null){
            return ;
        }
        dfs(root.left);
        dfs(root.right);
        int sc = root.val + noSelect.getOrDefault(root.left, 0) + noSelect.getOrDefault(root.right, 0);
        select.put(root, sc);
        int noSc = Math.max(select.getOrDefault(root.left, 0), noSelect.getOrDefault(root.left, 0)) +
                Math.max(select.getOrDefault(root.right, 0), noSelect.getOrDefault(root.right, 0));
        noSelect.put(root, noSc);
    }
}
