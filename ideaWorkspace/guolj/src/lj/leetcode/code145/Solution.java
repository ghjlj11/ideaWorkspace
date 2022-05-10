package lj.leetcode.code145;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author 86187
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> total = new ArrayDeque<>();
        TreeNode p = null;
        while (!total.isEmpty() || root != null){
            if (root != null){
                total.push(root);
                root = root.left;
            }
            else {
                root = total.peek();
                if(root.right == null || root.right == p){
                    res.add(root.val);
                    total.pop();
                    p = root;
                    root = null;
                }
                else {
                    root = root.right;
                }
            }

        }
        return res;
    }
}
