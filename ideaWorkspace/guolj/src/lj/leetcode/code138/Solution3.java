package lj.leetcode.code138;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 */
class Solution3 {
    Map<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        map.put(null, null);
        Node tail = head;
        while (tail != null){
            Node clone = new Node(tail.val);
            map.put(tail, clone);
            tail = tail.next;
        }
        tail = head;
        while (tail != null){
            map.get(tail).next = map.get(tail.next);
            map.get(tail).random = map.get(tail.random);
            tail = tail.next;
        }
        return map.get(head);
    }
}
