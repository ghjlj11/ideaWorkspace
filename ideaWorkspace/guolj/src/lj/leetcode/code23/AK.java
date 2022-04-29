package lj.leetcode.code23;

public class AK {
    public static void main(String[] args){
        ListNode[] k=new ListNode[2];
        k[0]=new ListNode(1);
        k[0].next=new ListNode(3);
        k[0].next.next=new ListNode(6);
        k[1]=new ListNode(2);
        k[1].next=new ListNode(4);
        k[1].next.next=new ListNode(8);
        CK s=new CK();
        ListNode x=s.mergeKLists(k);
        while(x!=null){
            System.out.println(x.val);
            x=x.next;
        }
    }
}
