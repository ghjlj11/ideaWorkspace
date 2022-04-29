package lj.leetcode.code94;
import java.util.*;
/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){}
}
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if(root == null){
            return res;
        }
        dfs(res,root);
        return res;
    }
    private void dfs(List<Integer> res,TreeNode root){
        if(root == null){
            return;
        }
        if(root.left != null){
            dfs(res, root.left);
        }
        res.add(root.val);
        if( root .right != null){
            dfs(res, root.right);
        }
    }
    public List<Integer> treeNode (TreeNode root){
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> deque = new LinkedList<TreeNode>();
        while (root != null || !deque.isEmpty()){
            while (root != null){
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
