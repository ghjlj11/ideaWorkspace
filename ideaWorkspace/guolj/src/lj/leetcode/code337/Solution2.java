package lj.leetcode.code337;

/**
 * @author 86187
 */
public class Solution2 {
    public int rob(TreeNode root) {
        int[] k = dfs(root);
        return Math.max(k[0],k[1]);
    }
    private int[] dfs(TreeNode root){
        if(root == null){
            return new int[]{0,0};
        }
        int[] l = dfs(root.left);
        int[] r = dfs(root.right);
        int select = l[0] + r[0] + root.val;
        int noSc = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{noSc,select};
    }
}
