package lj.leetcode.code208;

import java.util.Map;

/**
 * @author 86187
 */
public class Trie2 {
    private Node[] map;
    class Node{
        boolean idEnd;
        Node[] next;
        public Node() {
        }
    }

    public Trie2() {
        map = new Node[26];
    }

    public void insert(String word) {
        int len = word.length();
        char[] chars = word.toCharArray();
        Node[] temp = map;
        for(int i = 0; i < len ; i ++){
            int k = chars[i] - 'a';
            if(temp[k] == null){
                temp[k] = new Node();
            }
            if(i == len - 1){
                temp[k].idEnd = true;
            }
            if(temp[k].next == null){
                temp[k].next = new Node[26];
            }
            temp = temp[k].next;
        }
    }

    public boolean search(String word) {
        int len = word.length();
        char[] chars = word.toCharArray();
        Node[] temp = map;
        for(int i = 0 ; i < len ; i ++){
            int k = chars[i] - 'a';
            if(temp == null || temp[k] == null){
                return false;
            }
            if(i == len - 1 && !temp[k].idEnd){
                return false;
            }
            temp = temp[k].next;
        }
        return true;
    }

    public boolean startsWith(String prefix) {
        Node[] temp = map;
        char[] chars = prefix.toCharArray();
        int len = prefix.length();
        for(int i = 0 ; i < len ; i ++){
            int k = chars[i] - 'a';
            if(temp == null || temp[k] == null){
                return false;
            }
            temp = temp[k].next;
        }
        return true;
    }
}
