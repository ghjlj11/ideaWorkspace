package lj.leetcode.code239;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
	public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len - k + 1];
        Deque<Integer> st = new ArrayDeque<>();

        for(int i = 0 ; i < k ; i ++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i]) {
                st.pop();
            }
            st.push(i);
        }
        res[0] = nums[st.getLast()];
        for(int i = k ,j = 1; i < len ; i ++, j ++) {
            while (!st.isEmpty() && nums[st.peek()] < nums[i] ) {
                st.pop();
            }
            st.push(i);
            if(st.getLast() + k - 1 < i) {
                st.removeLast();
            }
            res[j] = nums[st.getLast()];
        }
        return res;
    }
}
