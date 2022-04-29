package lj.leetcode.code253;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
    public int minMeetingRooms(int[][] intervals) {
    	int sum = 1 ;
    	int len = intervals.length;
    	Arrays.sort(intervals, (o1,o2) -> o1[0] - o2[0]);
    	List<Integer> que = new ArrayList<>();
    	List<Integer> index = new ArrayList<>();
    	que.add(0);
    	index.add(0);
    	for(int i = 0 ; i < len ; i ++) {
    		for(int j = 0 ; j < que.size() ; j ++) {
    			int t = que.get(j);
    			if(t <= intervals[i][0] && t != 0) {
    				que.set(j, 0);
    				index.add(j);
    			}
    		}
    	
    		if(index.size() == 0) {
    			sum ++;
    			index.add(que.size());
    			que.add(0);
    		}
    		int k = index.remove(0);
    		que.set(k, intervals[i][1]);
    	}
    	return sum;
    }
}
