package lj.leetcode.code25;

public class BL {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode[] mm=new ListNode[k];
        ListNode h=head;
        for(int i=0;i<k;i++){
            mm[i]=h;
            if(mm[i]==null){return head;}
            h=h.next;
        }
        mm[0].next=reverseKGroup(mm[k-1].next,k);
        for(int j=1;j<k;j++){
            mm[j].next=mm[j-1];
        }
        return mm[k-1];
    }
}
