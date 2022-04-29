package lj.leetcode.code143;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 86187
 */
class Solution {
    public void reorderList(ListNode head) {
        if(head == null){
            return;
        }
        Deque<ListNode> deu = new ArrayDeque<>();
        ListNode fast = head, slow = head, mid = slow;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        mid = slow;
        slow = slow.next;
        while (slow != null){
            deu.push(slow);
            slow = slow.next;
        }
        mid.next = null;
        ListNode temp = head;
        while (!deu.isEmpty()){
            ListNode k = deu.pop();
            k.next = temp.next;
            temp.next = k;
            temp = temp.next.next;
        }
    }
}
