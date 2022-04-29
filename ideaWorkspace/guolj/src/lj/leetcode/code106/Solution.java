package lj.leetcode.code106;

/**
 * @author 86187
 */
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        TreeNode res = new TreeNode(postorder[len - 1]);
        if(len > 1){
            build(res, inorder, postorder, 0, len - 1, 0, len - 2);
        }
        return res;
    }
    private void build(TreeNode root,int[] inorder, int[] postorder, int starI, int endI, int starP, int endP){
        if(starI > endI || starP > endP){
            return;
        }
        int i , remove;
        for(i = starI , remove = 0; i < endI ; i ++, remove ++){
            if(inorder[i] == root.val){
                break;
            }
        }
        if(remove > 0){
            root.left = new TreeNode(postorder[starP + remove - 1]);
            build(root.left, inorder, postorder, starI, i - 1,  starP,starP + remove - 2);
        }
        if (starP + remove <= endP){
            root.right = new TreeNode(postorder[endP]);
            build(root.right, inorder, postorder, i + 1, endI, starP + remove , endP - 1);
        }
    }
}
