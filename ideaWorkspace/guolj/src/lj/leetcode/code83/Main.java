package lj.leetcode.code83;

public class Main {
    public static void main(String[] args){
        ListNode h=new ListNode(1,new ListNode(1));
        h.next.next=new ListNode(2);
        Solution j=new Solution();
        ListNode k=j.deleteDuplicates(h);
        while (k!=null){
            System.out.println(k.val);
            k=k.next;
        }
    }
}
