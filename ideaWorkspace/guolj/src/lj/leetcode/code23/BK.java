package lj.leetcode.code23;

/**
 * @author 86187
 */
public class BK {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res=null;
        if(lists.length<1){return res;}
        for(int i=0;i<lists.length;i++){
            res=he(res,lists[i]);
        }
        return res;
    }
    public ListNode he(ListNode l1,ListNode l2){
        ListNode h=new ListNode(0),t=h,a=l1,b=l2;
        while(a!=null&&b!=null){
            if(a.val>b.val){
                t.next=b;
                b=b.next;
            }else{
                t.next=a;
                a=a.next;
            }
            t=t.next;
        }
        t.next=(a==null?b:a);
        return h.next;
    }
}
