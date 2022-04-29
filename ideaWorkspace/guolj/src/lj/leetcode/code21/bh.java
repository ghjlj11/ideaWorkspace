package lj.leetcode.code21;

public class bh {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode h=new ListNode();
        ListNode t=h;
        while (list1!=null&&list2!=null){
            if(list1.val<list2.val){
                t.next=list1;
                list1=list1.next;
                System.out.println("111111111");
                t=t.next;
            }
            else{
                t.next=list2;
                list2=list2.next;
                System.out.println("22222222");
                t=t.next;
            }
        }
        if(list1==null){
            t.next=list2;
        }
        else{
            t.next=list1;
        }
        System.out.println("====================");
        return h.next;
    }
}
