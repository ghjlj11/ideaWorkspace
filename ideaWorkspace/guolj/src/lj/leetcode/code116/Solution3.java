package lj.leetcode.code116;

/**
 * @author 86187
 */
class Solution3 {
    public Node connect(Node root) {
        Node head = root, temp;
        while (head != null){
            temp = head.left;
            while (head != null){
                if(head.left != null){
                    head.left.next = head.right;
                    if(head.next != null){
                        head.right.next = head.next.left;
                    }
                }
                head = head.next;
            }
            head = temp;
        }
        return root;
    }
}
