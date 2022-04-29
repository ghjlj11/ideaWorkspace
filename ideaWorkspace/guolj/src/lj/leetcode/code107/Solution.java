package lj.leetcode.code107;

import java.util.*;

/**
 * @author 86187
 */

class Solution {
    int deep;
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        deep = 0 ;
        res.add(new ArrayList<>());
        addRes(res, root);
        Collections.reverse(res);
        return res;
    }
    private void addRes(List<List<Integer>> res, TreeNode root){
        if(root == null){
            return;
        }
        if(deep == res.size()){
            res.add(new ArrayList<>());
        }
        res.get(deep ++).add(root.val);
        addRes(res, root.left);
        addRes(res, root.right);
        deep --;
    }
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);
        while (!deque.isEmpty()){
            int size = deque.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = deque.pop();
                list.add(temp.val);
                if(temp.left != null){
                    deque.add(temp.left);
                }
                if(temp.right != null){
                    deque.add(temp.right);
                }
            }
            res.add(new ArrayList<>(list));
        }
        Collections.reverse(res);
        return res;
    }
}
