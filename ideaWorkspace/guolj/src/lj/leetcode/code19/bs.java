package lj.leetcode.code19;

public class bs {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode k=new ListNode(0,head);
        ListNode l=k,r=k;
        for(int i=0;i<n;i++){
            r=r.next;
        }
        while(r.next!=null){
            r=r.next;
            l=l.next;
        }
        l.next=l.next.next;
        return k.next;
    }
}