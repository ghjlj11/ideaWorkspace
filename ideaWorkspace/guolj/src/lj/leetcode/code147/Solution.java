package lj.leetcode.code147;

import java.util.*;

class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode res = new ListNode(0, head);
        ListNode tail = head.next , la = head;
        while (tail != null){
            ListNode t = res.next, p = null;
            boolean flag = false;
            while (t != tail){
                if(t.val > tail.val){
                    flag = true;
                    la.next = tail.next;
                    tail.next = t;
                    if(p != null){
                        p.next = tail;
                    }
                    else {
                        res.next = tail;
                    }
                    break;
                }
                p = t;
                t = t.next;
            }
            if(!flag){
                la = la.next;
            }
            tail = la.next;
        }
        return res.next;
    }
}
