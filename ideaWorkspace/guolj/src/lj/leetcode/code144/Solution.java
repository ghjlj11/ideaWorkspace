package lj.leetcode.code144;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
class Solution {
    List<Integer> res = new ArrayList<>();
    public List<Integer> preorderTraversal(TreeNode root) {
        if(root == null){
            return res;
        }
        res.add(root.val);
        res = preorderTraversal(root.left);
        res = preorderTraversal(root.right);
        return res;
    }
}
