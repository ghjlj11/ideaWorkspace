package lj.leetcode.code253;

import java.util.Arrays;

public class Solution2 {
    public int minMeetingRooms(int[][] intervals) {
    	int res = 0;
    	int len = intervals.length;
    	int[] star = new int[len];
    	int[] end = new int[len];
    	for(int i = 0 ; i < len ; i ++) {
    		star[i] = intervals[i][0];
    		end[i] = intervals[i][1];
    	}
    	Arrays.sort(star);
    	Arrays.sort(end);
    	int i = 0, j = 0;
    	int temp = 0;
    	while(i < len) {
    		if(end[j] <= star[i]) {
    			temp--;
    			j++;
    		}
    		else {
				i ++;
				temp ++;
				res = Math.max(res, temp);
			}
    	}
    	return res;
    }
}
