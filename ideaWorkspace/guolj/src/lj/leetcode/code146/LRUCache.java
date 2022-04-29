package lj.leetcode.code146;

import java.util.*;

public class LRUCache {

    private final int capacity;
    private MyNode head;
    private MyNode tail;
    private Map<Integer, MyNode> map;

    private HashMap<Integer, Integer> k = new LinkedHashMap<>();
    private class MyNode{
        int key;
        int value;
        MyNode pare;
        MyNode next;
        public MyNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public MyNode() {
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new MyNode();
        tail = new MyNode();
        head.next = tail;
        tail.pare = head;
    }

    public int get(int key) {
        MyNode temp = map.get(key);
        if(temp == null){
            return -1;
        }
        else {
            remove(temp);
            addFirst(temp);
            return temp.value;
        }
    }

    public void put(int key, int value) {
        MyNode temp = map.get(key);
        MyNode k = new MyNode(key, value);
        map.put(key,k);
        if(temp != null){
            remove(temp);
        }
        else {
            if(map.size() > capacity){
                MyNode temp2 = tail.pare;
                remove(temp2);
                map.remove(temp2.key);
            }
        }
        addFirst(k);
    }

    private void remove (MyNode temp){
        temp.pare.next = temp.next;
        temp.next.pare = temp.pare;
    }

    private void addFirst(MyNode temp){
        temp.pare = head;
        temp.next = head.next;
        head.next.pare = temp;
        head.next = temp;
    }
}
