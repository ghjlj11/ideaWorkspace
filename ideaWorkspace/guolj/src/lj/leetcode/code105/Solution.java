package lj.leetcode.code105;

/**
 * @author 86187
 */
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode res = new TreeNode(preorder[0]);
        int len = preorder.length;
        bfs(res, preorder, inorder, 1, len - 1, 0, len - 1);
        return res;
    }
    private void bfs(TreeNode root, int[] preorder, int[] inorder, int starP, int endP, int starI, int endI){
        if(starP > endP || starI > endI){
            return;
        }
        int i = starI,remove;
        for(i = starI, remove = 0 ; i <= endI; i ++, remove ++){
            if(inorder[i] == root.val){
                break;
            }
        }
        if(remove > 0) {
            root.left = new TreeNode(preorder[starP]);
            bfs(root.left, preorder, inorder, starP + 1, starP + remove - 1, starI, i - 1);
        }

        if(starP + remove  <= endP){
            root.right = new TreeNode(preorder[starP + remove ]);
            bfs(root.right, preorder, inorder, starP + remove + 1, endP, i + 1, endI);
        }
    }
}
