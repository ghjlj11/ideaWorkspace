package lj.leetcode.code221;

import java.util.Arrays;

/**
 * @author 86187
 */
public class Solution {
    public int maximalSquare(char[][] matrix) {
    	int res = 0 ;
    	int len = matrix.length;
    	if(len == 0 ) {
    		return 0;
    	}
    	int len1 = matrix[0].length;
    	int[][] dp = new int[len][len1];
    	for(int i = 0 ; i < len1 ; i ++) {
    		if(matrix[0][i] == '1') {
    			dp[0][i] = 1;
                res = 1;
    		}
    	}
    	for(int i = 0 ; i < len ; i ++) {
    		if(matrix[i][0] == '1') {
    			dp[i][0] = 1;
                res = 1;
    		}
    	}
    	
    	for(int i = 1 ; i < len ; i ++) {
    		for(int j = 1 ; j < len1 ; j ++) {
    			if(matrix[i][j] == '1') {
    				dp[i][j] = Math.min(dp[i][j - 1],Math.min(dp[i - 1][j - 1], dp[i - 1][j])) + 1;
    				res = Math.max(res, dp[i][j]);
    			}
    			else {
					dp[i][j] = 0;
				}
    		}
    	}
    	return res * res;
    }
}
