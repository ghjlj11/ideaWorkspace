package lj.leetcode.offer.offer35;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86187
 */
public class Solution {
    public Node copyRandomList(Node head) {
        Node tail = head;
        Node newHead = new Node(0);
        Node newTail = newHead;
        List<Node> total = new ArrayList<Node>();
        while (tail != null){
            newTail.next = new Node(tail.val);
            newTail = newTail.next;
            total.add(newTail);
            tail = tail.next;
        }
        tail = head;
        newTail = newHead.next;
        while (tail != null){
            int index = 0;
            Node temp = head;
            if(tail.random == null){
                newTail.random = null;
            }
            else {
                while (tail.random != temp){
                    index ++;
                    temp = temp.next;
                }
                newTail.random = total.get(index);
            }
            tail = tail.next;
            newTail = newTail.next;
        }
        return newHead.next;
    }
    public Node copy(Node head){
        Map<Node, Node> map = new HashMap<Node, Node>();
        Node tail = head;
        while (tail != null){
            map.put(tail, new Node(tail.val));
            tail = tail.next;
        }
        map.put(null, null);
        tail = head;
        while (tail != null){
            map.get(tail).next = map.get(tail.next);
            map.get(tail).random = map.get(tail.random);
            tail = tail.next;
        }
        return map.get(head);
    }
    public Node copy2(Node head){
        Node copy;
        if(head == null){
            return null;
        }
        Node tail = head;
        while (tail != null){
            copy = new Node(tail.val);
            copy.next = tail.next;
            tail.next = copy ;
            tail = tail.next.next;
        }
        tail = head;
        while (tail != null){
            if(tail.random != null){
                tail.next.random = tail.random.next;
            }
            tail = tail.next.next;
        }
        tail = head;
        Node newHead = head.next;
        Node newTail = newHead;
        while (tail != null){
            tail.next = tail.next.next;
            tail = tail.next;
            if(newTail.next != null){
                newTail.next = newTail.next.next;
                newTail = newTail.next;
            }
        }
        return newHead;
    }
}
