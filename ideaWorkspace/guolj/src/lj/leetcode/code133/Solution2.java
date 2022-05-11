package lj.leetcode.code133;

import java.util.*;

/**
 * @author 86187
 */
class Solution2 {
    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        Deque<Node> deq = new ArrayDeque<>();
        deq.push(node);
        while (!deq.isEmpty()){
            Node node1 = deq.pop();
            if (!map.containsKey(node1)) {
                Node clone = new Node(node1.val, new ArrayList<>());
                map.put(node1, clone);
            }
            for (Node neighbor : node1.neighbors) {
                if (!map.containsKey(neighbor)) {
                    deq.push(neighbor);
                    Node clone2 = new Node(neighbor.val, new ArrayList<>());
                    map.put(neighbor, clone2);
                }
                map.get(node1).neighbors.add(map.get(neighbor));
            }
        }
        return map.get(node);
    }
}
