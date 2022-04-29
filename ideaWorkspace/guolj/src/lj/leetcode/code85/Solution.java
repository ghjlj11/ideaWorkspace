package lj.leetcode.code85;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author 86187
 */
public class Solution {
    public int maximalRectangle(char[][] matrix) {
    	int res = 0 ;
    	if(matrix.length == 0 || matrix[0].length == 0){
    		return 0;
    	}
    	int len = matrix.length;
    	int len1 = matrix[0].length;
    	int[] dp = new int[len1];
    	for(int i = 0 ; i < len ; i ++) {
    		for(int j = 0 ; j < len1 ; j ++) {
    			if(matrix[i][j] == '1') {
    				dp[j] ++;
    			}
    			else {
    				dp[j] = 0;
    			}
    		}
    		res = Math.max(res, lagest(dp));
    	}
    	return res;
    }
    
    private int lagest(int[] n) {
    	int res = 0 ;
    	int len = n.length;
    	int[] le = new int[len];
    	int[] ri = new int [len];
    	Deque<Integer> st = new ArrayDeque<>();
    	for(int  i = 0 ; i < len ; i ++) {
    		while(!st.isEmpty() && n[st.peek()] >= n[i]) {
    			st.pop();
    		}
    		le[i] = st.isEmpty() ? -1 : st.peek();
    		st.push(i);
    	}
    	st.clear();
    	for(int i = len - 1 ; i >= 0 ; i -- ) {
    		while(!st.isEmpty() && n[st.peek()] >= n[i]) {
    			st.pop();
    		}
    		ri[i] = st.isEmpty() ? len - 1 : st.peek() - 1;
    		st.push(i);
    	}
    	
    	for(int i = 0 ; i < len ; i ++) {
    		int k = n[i] * (ri[i] - le[i]);
    		res = Math.max(res, k);
    	}
    	return res;
    }
}
