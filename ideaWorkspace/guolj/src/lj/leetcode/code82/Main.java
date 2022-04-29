package lj.leetcode.code82;

/**
 * @author 86187
 */
public class Main {
}
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p1 = new ListNode(0), p2, res = p1;
        p2 = head;
        if(p2.next == null){
            return head;
        }
        ListNode p3 = p2.next;

        while (p3 != null){
            while (p3!=null && p2.val == p3.val){
                while (p3!=null && p2.val == p3.val){
                    p3 = p3.next;
                }
                p2 = p3;
                p3 = p3 != null? p3.next : null;
            }
            p1.next = p2;
            p1 = p1.next;
            p2 = p3;
            p3 = p3 !=null? p3.next : null;
        }
        if(p2 != null){
            if(p1.val != p2.val){
                p1.next = p2;
            }
        }
        return res.next;
    }
}
