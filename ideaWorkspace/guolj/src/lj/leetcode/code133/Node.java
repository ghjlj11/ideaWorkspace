package lj.leetcode.code133;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
class Node {
    public int val;
    public List<Node> neighbors;

    public Node(){
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int val){
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public Node(int val, List<Node> neighbors){
        this.val = val;
        this.neighbors = neighbors;
    }
}
