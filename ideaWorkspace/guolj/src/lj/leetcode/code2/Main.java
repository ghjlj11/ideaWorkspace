package lj.leetcode.code2;

public class Main {
    public static void main(String[] args){
        ListNode l1,l;
        l=l1=new ListNode(3);
        //l1.next=new ListNode(9);
        //l1.next.next= new ListNode(9);
        ListNode l2=new ListNode(9);
        //l2.next=new ListNode(9);
        //l2.next.next= new ListNode(4);
        for(int i=0;i<3;i++){
            l.next=new ListNode(4);
            l=l.next;
        }
        Solution p=new Solution();
        ListNode k=p.addTwoNumbers(l1,l2);
        System.out.print("[");
        while(k!=null){
            System.out.print(k.val+",");
            k=k.next;
        }
        System.out.print("]");
    }
}
