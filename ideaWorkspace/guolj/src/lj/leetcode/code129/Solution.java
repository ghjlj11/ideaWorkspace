package lj.leetcode.code129;

/**
 * @author 86187
 */
class Solution {
    private int sum = 0 ;
    public int sumNumbers(TreeNode root) {
        if(root == null){
            return sum;
        }
        dfs(root, 0);
        return sum;
    }
    private void  dfs(TreeNode root, int temp){

        temp = temp * 10 + root.val;
        if(root.left == null && root.right == null){
            sum += temp;
        }
        else {
            if(root.left != null){
                dfs(root.left, temp);
            }
            if (root.right != null){
                dfs(root.right, temp);
            }
        }
    }
}
