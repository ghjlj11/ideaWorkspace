package lj.leetcode.code86;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){}
}
class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode smallHead = new ListNode(0);
        ListNode bigHead = new ListNode(0);
        ListNode smallTail = smallHead;
        ListNode bigTail = bigHead;
        while (head !=null){
            if(head.val < x){
                smallTail.next = head;
                smallTail = smallTail.next;
            }
            else {
                bigTail.next = head;
                bigTail = bigTail.next;
            }
            head = head.next;
        }
        smallTail.next = bigHead.next;
        return smallHead.next;
    }
}
