package lj.leetcode.code83;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode h=head;
        if(h==null||h.next==null){return h;}
        else {
            if(h.val==h.next.val){
                h=h.next;
                h=deleteDuplicates(h);
            }
            else {
                h.next=deleteDuplicates(h.next);
            }
        }
        return h;
    }
}
