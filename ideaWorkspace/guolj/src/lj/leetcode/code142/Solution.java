package lj.leetcode.code142;

/**
 * @author 86187
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode flow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            flow = flow.next;
            fast = fast.next.next;
            if(fast == flow){
                ListNode res = head;
                while (res != flow){
                    res = res.next;
                    flow = flow.next;
                }
                return res;
            }
        }
        return null;
    }
}
