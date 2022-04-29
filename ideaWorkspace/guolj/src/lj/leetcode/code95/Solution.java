package lj.leetcode.code95;
import java.util.*;
class Solution {
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new ArrayList<TreeNode>();
        return dfs(1, n);
    }
    private List<TreeNode> dfs(int l, int r){
        List<TreeNode> ans = new ArrayList<TreeNode>();
        if(l > r){
            ans.add(null);
            return ans;
        }
        for(int i = l; i <= r; i++){
            List<TreeNode> left = dfs(l, i - 1 );
            List<TreeNode> right = dfs(i + 1,r);
            for(TreeNode leftTree : left){
                for(TreeNode rightTree : right){
                    TreeNode current = new TreeNode(i);
                    current.left = leftTree;
                    current.right = rightTree;
                    ans.add(current);
                }
            }
        }
        return ans;
    }
}
