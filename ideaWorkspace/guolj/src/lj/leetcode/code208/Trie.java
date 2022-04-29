package lj.leetcode.code208;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 */
public class Trie {

    private Map<Character, Node> map;

    class Node{
        char val;
        Map<Character, Node> next;

        public Node() {
        }

        public Node(char val) {
            this.val = val;
        }
    }
    public Trie() {
        map = new HashMap<>();
    }

    public void insert(String word) {
        int len = word.length();
        char[] array = word.toCharArray();
        int i = 0;
        Map<Character,Node> temp = map;
        while (i < len && temp.get(array[i]) != null){
            if(temp.get(array[i]).next == null){
                temp.get(array[i]).next = new HashMap<>();
            }
            temp = temp.get(array[i]).next;
            i ++;
        }
        while (i < len){
            Node k = new Node(array[i]);
            temp.put(array[i], k);
            k.next = new HashMap<>();
            temp = k.next;
            i ++;
        }
        temp.put(null, null);
    }

    public boolean search(String word) {
        Map<Character, Node> temp = map;
        boolean[] f = new boolean[1];
        temp = isSearch(temp, word, f);
        return f[0] && temp != null && temp.containsKey(null);
    }

    public boolean startsWith(String prefix) {
        Map<Character, Node> temp = map;
        boolean[] f = new boolean[1];
        isSearch(temp, prefix,f);
        return f[0];
    }
    private Map<Character, Node> isSearch(Map<Character, Node> temp, String s,boolean[] b){
        int len = s.length();
        char[] array = s.toCharArray();
        for(int i = 0 ; i < len ; i ++){
            if(temp == null || temp.get(array[i]) == null){
                b[0] = false;
                return temp;
            }
            temp = temp.get(array[i]).next;
        }
        b[0] = true;
        return temp;
    }
}
