package lj.leetcode.code138;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 */
class Solution2 {
    Map<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        if(head == null){
            return null;
        }
        if(!map.containsKey(head)){
            Node clone = new Node(head.val);
            map.put(head, clone);
            clone.next = copyRandomList(head.next);
            clone.random = copyRandomList(head.random);
        }
        return map.get(head);
    }
}
