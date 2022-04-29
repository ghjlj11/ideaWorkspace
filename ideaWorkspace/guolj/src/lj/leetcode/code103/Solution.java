package lj.leetcode.code103;
import java.util.*;

/**
 * @author 86187
 */
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> temp = new LinkedList<TreeNode>();
        Deque<TreeNode> temp1 = new LinkedList<TreeNode>();
        temp.push(root);
        int j = 0;
        while (!temp.isEmpty()){
            int size = temp.size();
            List<Integer> ans = new ArrayList<Integer>();
            for(int i = 0 ;i < size; i ++){
                TreeNode x = temp.removeFirst();
                ans.add(x.val);
                if(j%2 == 1){
                    if(x.right != null){
                        temp1.addLast(x.right);
                    }
                    if(x.left != null){
                        temp1.addLast(x.left);
                    }
                }
                else {
                    if(x.left != null){
                        temp1.addLast(x.left);
                    }
                    if(x.right != null){
                        temp1.addLast(x.right);
                    }
                }
            }
            while (!temp1.isEmpty()){
                temp.addFirst(temp1.removeFirst());
            }
            j++;
            res.add(new ArrayList<>(ans));
        }
        return res;
    }
    public List<List<Integer>> order(TreeNode root){
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> temp1 = new LinkedList<TreeNode>();
        temp1.add(root);
        int  j = 0;
        while (!temp1.isEmpty()){
            int size = temp1.size();
            Deque<Integer> temp2 = new LinkedList<Integer>();
            for(int i = 0 ; i < size ; i ++){
                TreeNode x = temp1.removeFirst();
                if(j % 2 == 0){
                    temp2.addLast(x.val);
                }
                else {
                    temp2.addFirst(x.val);
                }
                if(x.left != null){
                    temp1.addLast(x.left);
                }
                if(x.right != null){
                    temp1.addLast(x.right);
                }
            }
            j++;
            res.add(new ArrayList<>(temp2));
        }
        return res;
    }
}
