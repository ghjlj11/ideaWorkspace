package lj.leetcode.code116;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 86187
 */
class Solution {
    public Node connect(Node root) {
        if(root == null){
            return null;
        }
        Deque<Node> deq = new ArrayDeque<>();
        deq.add(root);
        while (!deq.isEmpty()){
            int size = deq.size();
            Node temp1 = deq.removeFirst(), temp2;
            for(int i = 1 ; i <= size ; i ++){
                temp2 = temp1;
                if(temp2.left != null){
                    deq.add(temp2.left);
                    deq.add(temp2.right);
                }
                temp1 = i == size ? null : deq.removeFirst();
                temp2.next = temp1;
            }
        }
        return root;
    }
}
