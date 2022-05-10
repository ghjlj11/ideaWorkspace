package lj.leetcode.code144;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author 86187
 */
class Solution3 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> sta = new ArrayDeque<>();
        while (root != null || !sta.isEmpty()){
            if(root != null){
                res.add(root.val);
                sta.push(root);
                root = root.left;
            }
            else {
                root = sta.pop();
                root = root.right;
            }
        }
        return res;
    }
}
