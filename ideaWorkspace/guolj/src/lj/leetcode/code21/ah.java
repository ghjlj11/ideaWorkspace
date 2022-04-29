package lj.leetcode.code21;

public class ah {
    public static void main(String[] args){
        int[] n={2,4,5,7,8},m={1,3,5,7,9};
        ListNode s1=new ListNode(),s2=new ListNode();
        ListNode l1=s1,l2=s2;
        for(int i=0;i<5;i++){
            l1.next=new ListNode(n[i]);
            l2.next=new ListNode(m[i]);
            l1=l1.next;
            l2=l2.next;
        }
        l1=s1.next;
        l2=s2.next;
        while(l1!=null){
            System.out.println(l1.val+"  ");
            l1=l1.next;
        }
        System.out.println(" ------------------------- ");
        while(l2!=null){
            System.out.println(l2.val+"  ");
            l2=l2.next;
        }
        System.out.println(" ------------------------- ");
        bh e=new bh();
        ListNode k=e.mergeTwoLists(s1.next,s2.next);
        while(k!=null){
            System.out.println(k.val+"  ");
            k=k.next;
        }
    }
}
