package lj.leetcode.code144;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author 86187
 */
class Solution2 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> deq = new ArrayDeque<>();
        deq.addLast(root);
        while (!deq.isEmpty()){
            TreeNode temp = deq.removeLast();
            if(temp.right != null){
                deq.addLast(temp.right);
            }
            if(temp.left != null){
                deq.addLast(temp.left);
            }
            res.add(temp.val);
        }
        return res;
    }
}
