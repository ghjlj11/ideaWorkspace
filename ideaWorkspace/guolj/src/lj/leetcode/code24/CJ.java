package lj.leetcode.code24;

public class CJ {
    public ListNode swapPairs(ListNode head) {
        if(head==null||head.next==null){return head;}
        ListNode k;
        k=head.next;
        head.next=k.next;
        k.next=head;
        k.next.next=swapPairs(k.next.next);
        return k;
    }
}
