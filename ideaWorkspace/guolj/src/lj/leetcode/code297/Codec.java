package lj.leetcode.code297;

import java.util.*;

/**
 * @author 86187
 */
public class Codec {

    public String serialize(TreeNode root) {
        StringBuilder res  = new StringBuilder();
        serHelp(root, res);
        return res.toString();
    }

    private void serHelp(TreeNode root , StringBuilder res){
        if(root == null){
            res.append("null,");
            return;
        }
        res.append(root.val).append(",");
        serHelp(root.left, res);
        serHelp(root.right, res);
    }

    public TreeNode deserialize(String data) {
        TreeNode res = new TreeNode();
        String[] split = data.split(",");
        List<String> list = new ArrayList<>(Arrays.asList(split));
        return desHelp(res, list);
    }
    private TreeNode desHelp(TreeNode root, List<String> list){
        if("null".equals(list.get(0))){
            list.remove(0);
            return null;
        }
        int k = Integer.parseInt(list.get(0));
        list.remove(0);
        root = new TreeNode(k);
        root.left = desHelp(root.left, list);
        root.right = desHelp(root.right, list);
        return root;
    }
}
