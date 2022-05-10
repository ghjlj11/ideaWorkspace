package lj.leetcode.code145;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Mid {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> sta = new ArrayDeque<>();

        while (!sta.isEmpty() || root != null){
            if(root != null){
                sta.push(root);
                root = root.left;
            }
            else {
                root = sta.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}
