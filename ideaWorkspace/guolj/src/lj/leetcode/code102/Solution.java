package lj.leetcode.code102;
import java.util.LinkedList;
import java.util.List;
import  java.util.ArrayList;
import java.util.Queue;

/**
 * @author 86187
 */
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        dfs(res, 0, root);
        return res;
    }
    private void dfs(List<List<Integer>> res, int times , TreeNode root){
        if(root == null){
            return;
        }
        if(times == res.size()){
            List<Integer> temp = new ArrayList<Integer>();
            res.add(temp);
        }
        res.get(times).add(root.val);
        dfs(res, times + 1, root.left);
        dfs(res, times + 1, root.right);
    }
    public List<List<Integer>> level(TreeNode root){
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> temp = new ArrayList<Integer>();
            for(int i = 0; i < size; i ++){
                TreeNode x = queue.poll();
                temp.add(x.val);
                if(x.left != null){
                    queue.add(x.left);
                }
                if(x.right != null){
                    queue.add(x.right);
                }
            }
            res.add(temp);
        }
        return res;
    }
}
