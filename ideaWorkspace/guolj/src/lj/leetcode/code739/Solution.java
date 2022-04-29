package lj.leetcode.code739;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author 86187
 */
public class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] res = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0 ; i < len ; i ++){
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int k = stack.pop();
                res[k] = i - k;
            }
            stack.push(i);
        }
        return res;
    }
}
