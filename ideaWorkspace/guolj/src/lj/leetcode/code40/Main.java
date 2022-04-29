package lj.leetcode.code40;

import java.util.*;
public class Main {
    public static void main(String[] args){
        int[] n={10,1,2,7,6,1,5};
        int t=8;
        Solution D=new Solution();
        System.out.println(D.combinationSum2(n,t));
    }
}
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        List<Integer> a=new ArrayList<Integer>();
        Arrays.sort(candidates);
        int x=0;
        int n=candidates.length;
        pp(candidates,target,res,a,x,n);
        return res;
    }
    private void pp(int[] candidates,int target,List<List<Integer>> res,List<Integer> a,int x,int n){
        if(target==0){
            res.add(new ArrayList<Integer>(a));
            return;
        }
        for(int i=x;i<n;i++){
            if(target-candidates[i]<0){
                break;
            }
            if(i>x&&candidates[i]==candidates[i-1]){
                continue;
            }
            a.add(candidates[i]);
            pp(candidates,target-candidates[i],res,a,i+1,n);
            a.remove(a.size()-1);
        }
    }
}
