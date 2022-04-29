package lj.leetcode.code92;
import java.util.*;
/**
 * @author 86187
 */
public class Main {
    public static void main (String[] args){}
}
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if( head.next == null){
            return head;
        }
        ListNode l = head , tail = head;
        List<Integer> list = new ArrayList<Integer>();
        int i = 1;
        while ( i <= right){
            if( i == left - 1){
                l = tail;
            }
            if( i >= left){
                list.add(tail.val);
            }
            tail = tail.next;
            i++;
        }
        ListNode center = new ListNode(0),newTail = center;
        int k = 1;
        while (k <= list.size()){
            newTail.next = new ListNode(list.get(list.size() - k));
            newTail = newTail.next;
            k++;
        }
        if(left == 1){
            newTail.next = tail;
            return center.next;
        }
        newTail.next = tail;
        l.next = center.next;
        return head;
    }
    private ListNode reverse(ListNode head){
        ListNode newHead = null;
        while(head != null){
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }
}
