package lj.leetcode.code23;

public class DK {
    public ListNode mergeKLists(ListNode[] lists) {
        return kk(lists,0,lists.length-1);
    }
    public ListNode kk(ListNode[] lists,int l,int r){
        if(l==r){
            return lists[l];
        }
        if(lists==null||l>r){
            return null;
        }
        int mid=(l+r)/2;
        return jj(kk(lists,l,mid),kk(lists,mid+1,r));
    }
    public ListNode jj(ListNode l1,ListNode l2){
        ListNode h=new ListNode(0);
        ListNode t=h,a=l1,b=l2;
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
        t.next=a==null?b:a;
        return h.next;
    }
}
