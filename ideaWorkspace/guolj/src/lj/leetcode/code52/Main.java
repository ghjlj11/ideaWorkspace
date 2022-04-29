package lj.leetcode.code52;
import java.util.*;
public class Main {
    public static void main(String[] args){
        int n=4;
        Solution d=new Solution();
        System.out.println(d.totalNQueens(n));
    }
}
class Solution {
    public int totalNQueens(int n) {
        Set<Integer> cols=new HashSet<>();
        Set<Integer> xx1=new HashSet<>();
        Set<Integer> yy1=new HashSet<>();
        int[] queue=new int[n];
        Arrays.fill(queue,-1);
        return pad(0,n,0,queue,cols,xx1,yy1);
    }
    private int pad(int ans,int n,int row,int[] queue,Set<Integer> cols,Set<Integer> xx1,Set<Integer> yy1) {
        if(row==n){
            return ans+1;
        }
        else {
            for(int i=0;i<n;i++){
                if(cols.contains(i)){continue;}
                if(xx1.contains(row+i)){continue;}
                if(yy1.contains(row-i)){continue;}
                queue[row]=i;
                cols.add(i);
                xx1.add(row+i);
                yy1.add(row-i);
                ans=pad(ans,n,row+1,queue,cols,xx1,yy1);
                queue[row]=-1;
                cols.remove(i);
                xx1.remove(row+i);
                yy1.remove(row-i);
            }
        }
        return ans;
    }
}
