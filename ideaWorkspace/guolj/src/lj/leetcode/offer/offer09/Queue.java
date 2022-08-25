package lj.leetcode.offer.offer09;
import java.util.LinkedList;
class Queue {
    LinkedList<Integer> s1;
    LinkedList<Integer> s2;
    public Queue() {
        s1=new LinkedList<Integer>();
        s2=new LinkedList<Integer>();
    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        if(s2.isEmpty()){return -1;}
        else{return s2.pop();}
    }
}
