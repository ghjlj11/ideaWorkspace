package lj.leetcode.code148;

/**
 * @author 86187
 */
public class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode cut = new ListNode(0,head);
        ListNode newHead = head;
        ListNode tail = head;
        //快慢指针找到链表的中点，以及中点的上一个结点；
        while (tail != null && tail.next != null){
            cut = cut.next;
            tail = tail.next.next;
            newHead = newHead.next;
        }
        //将链表分割成两个链表；
        cut.next = null;
        //新的两个头节点
        head = sortList(head);
        newHead = sortList(newHead);
        //归并排序;
        return merge(head, newHead);
    }

    /**
     *合并两个有序的链表的方法；
     */
    private ListNode merge(ListNode l1, ListNode l2){
        if(l1 == null || l2 == null){
            return l1 == null ? l2 : l1;
        }
        if(l1.val < l2.val){
            l1.next = merge(l1.next, l2);
            return l1;
        }
        else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}
