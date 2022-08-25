package lj.leetcode.offer.offer30;

import java.util.ArrayList;

/**
 * @author 86187
 */
public class MinStack {
    private ArrayList<Integer> values = new ArrayList<>();
    private ArrayList<Integer> min = new ArrayList<>();
    private int size = 0;
    public MinStack() {

    }

    public void push(int x) {
        values.add(x);
        if(min.isEmpty()){
            min.add(x);
        }
        else {
            if(x < min.get(min.size() - 1)){
                min.add(x);
            }
            else {
                min.add(min.get(min.size() - 1));
            }
        }
        size ++;
    }

    public void pop() {
        values.remove(size - 1);
        min.remove(size - 1);
        size--;
    }

    public int top() {
        return values.get(size - 1);
    }

    public int min() {
        return min.get(size - 1);
    }
}
