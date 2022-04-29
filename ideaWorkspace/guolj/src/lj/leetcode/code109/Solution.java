package lj.leetcode.code109;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author 86187
 */
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null){
            return null;
        }
        List<ListNode> arr = new ArrayList<>();
        ListNode tail = head;
        while (tail != null){
            arr.add(tail);
            tail = tail.next;
        }
        return dfs(arr, 0, arr.size() - 1);
    }
    private TreeNode dfs(List<ListNode> arr, int star, int end){
        if(star > end){
            return null;
        }
        int mid = ( star + end ) / 2;
        ListNode temp = arr.get(mid);
        TreeNode head = new TreeNode(temp.val);
        head.left = dfs(arr, star, mid - 1);
        head.right = dfs(arr, mid + 1, end);
        return head;
    }
}
