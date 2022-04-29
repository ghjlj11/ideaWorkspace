package lj.leetcode.code234;

/**
 * @author 86187
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null){
            return true;
        }
        ListNode fast = head.next, flow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            flow = flow.next;
        }
        ListNode newHead = reverse(flow.next);
        ListNode p = newHead;
        boolean k = true;
        while (k && p != null){
            if(head.val != p.val){
                k = false;
            }
            head = head.next;
            p = p.next;
        }
        flow.next = reverse(newHead);
        return k;
    }
    private ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode next;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
