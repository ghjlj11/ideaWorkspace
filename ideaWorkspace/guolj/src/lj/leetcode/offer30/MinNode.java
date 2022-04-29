package lj.leetcode.offer30;


/**
 * @author 86187
 */
public class MinNode {
    private Node head;
    public void push(int x){
        if(head == null){
            head = new Node(x, x);
        }
        else {
            int min = Math.min(x, head.min);
            head = new Node(x, min, head);
        }
    }
    public void pop(){
        head = head.next;
    }
    public int top(){
        return head.value;
    }
    public int min(){
        return head.min;
    }
    private class Node{
        int value;
        int min;
        Node next;
        public Node(int value, int min, Node next){
            this.min = min;
            this.value = value;
            this.next = next;
        }
        public Node(int value, int min){
            this.min = min;
            this.value = value;
        }
    }
}
