package lj.leetcode.code117;

/**
 * @author 86187
 */
class Solution2 {
    public Node connect(Node root) {
        Node head = root;
        Node temp = new Node(0);
        while (head != null){
            Node x = temp;
            while (head != null){
                if(head.left != null){
                    x.next = head.left;
                    x = x.next;
                }
                if(head.right != null){
                    x.next = head.right;
                    x = x.next;
                }
                head = head.next;
            }
            head = temp.next;
            temp.next = null;
        }
        return root;
    }
}
