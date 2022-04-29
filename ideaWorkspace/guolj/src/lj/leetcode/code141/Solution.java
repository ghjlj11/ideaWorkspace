package lj.leetcode.code141;

/**
 * @author 86187
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null ){
            return false;
        }
        ListNode tail  = head;
        int i = 200000;
        tail.val = i--;
        while(tail.next != null){
            int k = tail.next.val;
            if(tail == tail.next || k > tail.val){
                return true;
            }
            tail.next.val = i--;
            tail = tail.next;
        }
        return false;
    }
    public boolean cycle(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode flow = head;
        ListNode fast = head.next;
        while (flow != fast){
            if(fast == null || fast.next == null){
                return false;
            }
            fast = fast.next.next;
            flow = flow.next;
        }
        return true;
    }
}
