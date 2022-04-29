package lj.leetcode.code236;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
public class Solution {
    boolean flag = false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> p1 = new ArrayList<>();
        List<TreeNode> p2 = new ArrayList<>();
        p1.add(root);
        p2.add(root);
        find(p, root, p1);
        flag = false;
        find(q, root, p2);
        int min = Math.min(p1.size(), p2.size());
        TreeNode res = root;
        for(int i = p1.size() - 1 , j = p2.size() - 1 ; i >= 0 && j >= 0 ; i --, j--){
            TreeNode n = p1.get(i);
            TreeNode m = p2.get(j);
            if(n != m){
                break;
            }
            res = n;
        }
        return res;
    }
    private boolean find(TreeNode target, TreeNode root,  List<TreeNode> p1){
        if(root == null){
            return false;
        }
        if(root == target){
            flag = true;
            return true;
        }
        boolean res = false;
        if(! flag && find(target , root.left , p1)){
            p1.add(root.left);
            res = true;
        }
        if(!flag && find(target, root.right, p1)){
            p1.add(root.right);
            res = true;
        }
        return res;
    }
}
