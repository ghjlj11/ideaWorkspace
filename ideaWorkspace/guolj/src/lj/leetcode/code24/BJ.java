package lj.leetcode.code24;

public class BJ {
    public ListNode swapPairs(ListNode head) {
        return jh(head);
    }
    public ListNode jh(ListNode l1){
        if(l1==null||l1.next==null){return l1;}
        ListNode t;
        t=l1.next;
        l1.next=jh(t.next);
        t.next=l1;
        return t;
    }
}
