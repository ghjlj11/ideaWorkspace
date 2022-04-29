package lj.leetcode.code77;
import java.util.*;
public class Main {
    public static void main(String[] args){
        int n=4,k=2;
        Solution F=new Solution();
        System.out.println(F.combine(n,k));
    }
}
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        List<Integer> a=new ArrayList<Integer>();
        pp(n,k,0,res,a,1);
        return res;
    }
    private void pp(int n,int k,int x,List<List<Integer>> res,List<Integer> a,int o){
        if(x==k){
            res.add(new ArrayList<Integer>(a));
            return;
        }
        for(int i=o;i<=n;i++){
            a.add(i);
            pp(n,k,x+1,res,a,i+1);
            a.remove(a.size()-1);
        }
    }
}
