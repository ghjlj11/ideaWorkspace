package lj.leetcode.code138;

/**
 * @author 86187
 */
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        Node tail = head;

        while (tail != null){
            Node temp = tail.next;
            tail.next = new Node(tail.val);
            tail.next.next = temp;
            tail = tail.next.next;
        }
        tail = head;
        while (tail != null){
            tail.next.random = tail.random == null ? null : tail.random.next;
            tail = tail.next.next;
        }

        tail = head;
        Node newHead = tail.next, newTail = newHead;
        while (tail != null ){
            tail.next = newTail.next;
            newTail.next = tail.next == null ? null : tail.next.next;
            tail = tail.next;
            newTail = newTail.next;
        }
        return newHead;
    }
}
