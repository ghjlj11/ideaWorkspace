package lj.leetcode.offer.offer30;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){
        MinNode minStack = new MinNode();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.min());
    }
}
