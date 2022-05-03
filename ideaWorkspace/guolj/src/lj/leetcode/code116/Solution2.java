package lj.leetcode.code116;

/**
 * @author 86187
 */
public class Solution2 {
    public Node connect(Node root) {
        if (root == null){
            return null;
        }
        dfs(root);
        return root;
    }
    private void dfs(Node root){
        if(root.left == null){
            return;
        }
        root.left.next = root.right;
        if(root.next != null){
            root.right.next = root.next.left;
        }
        dfs(root.left);
        dfs(root.right);
    }
}
