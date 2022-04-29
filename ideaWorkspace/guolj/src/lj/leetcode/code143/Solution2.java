package lj.leetcode.code143;

/**
 * @author 86187
 */
class Solution2 {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        ListNode mid = findMid(head);
        ListNode h2 = mid.next;
        h2 = reverse(h2);
        mid.next = null;
        merge(head, h2);
    }
    private ListNode reverse(ListNode head){
        ListNode pre = null, next;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
    private ListNode findMid(ListNode head){
        ListNode fast = head.next,slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private void merge(ListNode h1, ListNode h2){
        while (h2 != null){
            ListNode next1 = h1.next;
            ListNode next2 = h2.next;
            h2.next = next1;
            h1.next = h2;
            h2 = next2;
            h1 = next1;
        }
    }
}
