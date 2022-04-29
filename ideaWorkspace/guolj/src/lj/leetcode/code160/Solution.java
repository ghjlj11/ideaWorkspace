package lj.leetcode.code160;

/**
 * @author 86187
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null){
            return null;
        }
        ListNode tail = headA;
        while (tail.next != null){
            tail = tail.next;
        }
        ListNode circle = tail;
        circle.next = headA;
        ListNode p1 = headB, p2 = headB;
        while (p2 != null && p2.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                break;
            }
        }
        if(p1 == p2 && p2.next == null){
            tail.next = null;
            return null;
        }
        ListNode a = headB;
        while ( a != p1){
            if(p1 == null){
                tail.next = null;
                return null;
            }
            a = a.next;
            p1 = p1.next;
        }
        tail.next = null;
        return a;
    }
    public ListNode gitNode(ListNode headA, ListNode headB){
        if(headA == null || headB == null){
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        while(p1 != p2){
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }
}
