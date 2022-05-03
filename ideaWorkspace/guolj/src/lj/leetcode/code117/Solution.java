package lj.leetcode.code117;

/**
 * @author 86187
 */
class Solution {
    public Node connect(Node root) {
        Node head = root, temp ;
        while (head != null){
            temp = head;
            Node n = null, l = null, r = null, t= null;
            while (head != null){
                l = head.left;
                r = head.right;
                if(l != null && r != null){
                    if(n != null){
                        n.next = l;
                    }
                    l.next = r;
                    t = r;
                }
                else {
                    t = l != null ? l : ( r != null ? r : t );
                    if(n != null && n != t){
                        n.next = t;
                    }
                }
                n = t;
                head = head.next;
            }
            while (temp != null && temp.left == null && temp.right == null){
                temp = temp.next;
            }
            head = temp == null ? null : (temp.left != null ? temp.left : temp.right);
        }
        return root;
    }
}
