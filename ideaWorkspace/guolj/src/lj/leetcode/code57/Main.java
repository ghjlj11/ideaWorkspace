package lj.leetcode.code57;
import java.util.*;
public class Main {
    public static void main(String[] args){
        int[][] s={{1,2},{3,5},{6,7},{8,10},{12,16}};
        int[] n={4,8};
        Solution A=new Solution();
        s=A.insert(s,n);
        for(int i=0;i<s.length;i++){
            for(int j=0;j<s[0].length;j++){
                System.out.println(s[i][j]);
            }
        }
    }
}
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int length=intervals.length;
        List<int[]> res=new ArrayList<int[]>();
        int index=0;
        while (index<length&&intervals[index][1]<newInterval[0]){
            res.add(intervals[index]);
            index++;
        }
        int left=newInterval[0], right=newInterval[1];
        if(index!=length){
            left=Math.min(left,intervals[index][0]);
        }
        while (index<length&&intervals[index][0]<=newInterval[1]){
            right=Math.max(right,intervals[index][1]);
            index++;
        }
        if(index!=0){
            right=Math.max(intervals[index-1][1],right);
        }
        res.add(new int[]{left,right});
        while (index<length){
            res.add(intervals[index]);
            index++;
        }
        return res.toArray(new int[res.size()][]);
    }
    public int[][] kk(int[][] intervals, int[] newInterval) {
        int length=intervals.length;
        List<int[]> res=new ArrayList<int[]>();
        int index=0;
        while (index<length&&intervals[index][1]<newInterval[0]){
            res.add(intervals[index]);
            index++;
        }
        int left=newInterval[0], right=newInterval[1];
        while (index<length&&intervals[index][0]<=newInterval[1]){
            left=Math.min(left,intervals[index][0]);
            right=Math.max(right,intervals[index][1]);
            index++;
        }
        res.add(new int[]{left,right});
        while (index<length){
            res.add(intervals[index]);
            index++;
        }
        int[][] ans = new int[res.size()][];
        return res.toArray(ans);
    }
}
