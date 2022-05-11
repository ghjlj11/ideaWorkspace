package lj.leetcode.code133;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86187
 */
class Solution {
    Map<Node, Node> map = new HashMap<>();
    public Node cloneGraph(Node node) {
        if(node == null){
            return null;
        }
        if(!map.containsKey(node)){
            Node clone = new Node(node.val, new ArrayList<>());
            map.put(node, clone);
            for (Node neighbor : node.neighbors) {
                clone.neighbors.add(cloneGraph(neighbor));
            }
        }
        return map.get(node);
    }
}
