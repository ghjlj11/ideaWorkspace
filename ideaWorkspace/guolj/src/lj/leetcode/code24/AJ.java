package lj.leetcode.code24;

public class AJ {
    public static void main(String[] args){
        ListNode l=new ListNode(0);
        l.next=new ListNode(1);
        l.next.next=new ListNode(2);
        l.next.next.next=new ListNode(3);
        l.next.next.next.next=new ListNode(4);
        CJ k=new CJ();
        ListNode x=k.swapPairs(l);
        while(x!=null){
            System.out.println(x.val);
            x=x.next;
        }
    }
}
