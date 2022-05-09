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
        if(root == null){
            return res;
        }
        Deque<TreeNode> total = new ArrayDeque<>();
        total.add(root);
        while (!total.isEmpty()){
            TreeNode t = total.pop();
        }
    }
}
