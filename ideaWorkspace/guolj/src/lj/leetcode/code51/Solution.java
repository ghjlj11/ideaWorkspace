package lj.leetcode.code51;
import java.util.*;
public class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res=new ArrayList<List<String>>();
        Set<Integer> cols=new HashSet<>();
        Set<Integer> xx1=new HashSet<>();
        Set<Integer> yy1=new HashSet<>();
        int[] queue=new int[n];
        Arrays.fill(queue,-1);
        pad(res,n,0,queue,cols,xx1,yy1);
        return res;
    }
    public void pad(List<List<String>> res,int n,int row,int[] queue,Set<Integer> cols,Set<Integer> xx1,Set<Integer> yy1) {
        if(row==n){
            res.add(jj(n,queue));
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
                pad(res,n,row+1,queue,cols,xx1,yy1);
                queue[row]=-1;
                cols.remove(i);
                xx1.remove(row+i);
                yy1.remove(row-i);
            }
        }
    }
    public List<String> jj(int n,int[] queue){
        List<String> ss=new ArrayList<String>();
        for(int i=0;i<n;i++){
            char[] a=new char[n];
            Arrays.fill(a,'.');
            a[queue[i]]='Q';
            String s=new String(a);
            ss.add(s);
        }
        return ss;
    }
}
