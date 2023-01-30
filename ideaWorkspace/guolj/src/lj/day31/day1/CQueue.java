package lj.day31.day1;

import java.util.Stack;

/**
 * @author 86187
 */
class CQueue {

    private final Stack<Integer> insertTail;
    private final Stack<Integer> deleteHead;
    public CQueue() {
        insertTail = new Stack<>();
        deleteHead = new Stack<>();
    }
    
    public void appendTail(int value) {
        insertTail.push(value);
    }
    
    public int deleteHead() {
        if(deleteHead.empty()){
            while (! insertTail.empty()){
                deleteHead.push(insertTail.pop());
            }
        }
        if(deleteHead.empty()) {
            return -1;
        }
        else {
            return deleteHead.pop();
        }
    }
}