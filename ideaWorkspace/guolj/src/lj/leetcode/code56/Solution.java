package lj.leetcode.code56;
import java.util.*;
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(v1,v2)->v1[0]-v2[0]);
        List<int[]> ans=new ArrayList<int[]>();
        int max=intervals[0][1],pose=0,i;
        for(i=1;i<intervals.length;i++){
            if(max<intervals[i][0]){
                ans.add(new int[]{intervals[pose][0],max});
                max=intervals[i][1];
                pose=i;
            }
            else {
                max=Math.max(max,intervals[i][1]);
            }
        }
        ans.add(new int[]{intervals[pose][0],max});
        return ans.toArray(new int[ans.size()][]);
    }
    public int[][] sort(int[][] intervals){
        int i = 0, length = intervals.length;
        if(length == 1){
            return intervals;
        }
        Arrays.sort(intervals,(v1,v2)->v1[0]-v2[0]);
        List<int[]> ans=new ArrayList<int[]>();
        int left = intervals[0][0],right = intervals[0][1];
        while (i < length ){
            if(intervals[i][0] <= right) {
                while (i < length && intervals[i][0] <= right){
                    right = Math.max(right, intervals[i][1]);
                    i++;
                }
                i--;
            }
            ans.add(new int[]{left, right});
            if(i < length - 1){
                i++;
            }
            else {
                break;
            }
            right = intervals[i][1];
            left = intervals[i][0];
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
class Main {
    public static void main(String[] args){
        int[][] n={{1,4},{4,5}};
        Solution A=new Solution();
        int[][] s=A.merge(n);
        for(int i=0;i<s.length;i++){
            for(int j=0;j<s[0].length;j++){
                System.out.println(s[i][j]);
            }
        }
    }
}
