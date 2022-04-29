package lj.leetcode.code84;

import java.util.ArrayDeque;
import java.util.Deque;
/**
 * @author 86187
 */
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0 ; i < len ; i ++){
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        stack.clear();
        for(int i = len - 1 ; i >= 0 ; i --){
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            right[i] = stack.isEmpty() ? len - 1 : stack.peek() - 1;
            stack.push(i);
        }
        int max = 0 ;
        for(int i = 0 ; i < len ; i ++){
            int temp = (right[i] - left[i] ) * heights[i];
            max = Math.max(max, temp);
        }
        return max;
    }
}
