package lj.leetcode.code23;
import java.util.Comparator;
import java.util.PriorityQueue;
public class CK {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null||lists.length==0){return null;}
        PriorityQueue<ListNode>s=new PriorityQueue<ListNode>(lists.length,new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) return -1;
                else if (o1.val == o2.val) return 0;
                else return 1;
            }
        });
        ListNode n=new ListNode(0),m=n;
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){s.add(lists[i]);}
        }
        while(!s.isEmpty()){
            m.next=s.poll();
            m=m.next;
            if(m.next!=null){s.add(m.next);}
        }
        return n.next;
    }
}
