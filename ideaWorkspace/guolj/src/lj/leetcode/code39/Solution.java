package lj.leetcode.code39;

import java.util.*;
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        List<Integer> a=new ArrayList<Integer>();
        int n=candidates.length;
        if(n==0){
            return res;
        }
        pp(a,res,n,0,target,candidates);
        return res;
    }
    private void pp(List<Integer> a,List<List<Integer>> res,int n,int star,int target,int[] candidates){
        if(target==0){
            List<Integer> x=new ArrayList<Integer>(a);
            res.add(x);
            return;
        }
        for(int i=star;i<n;i++){
            if(target-candidates[i]>=0){
                a.add(candidates[i]);
                pp(a,res,n,i,target-candidates[i],candidates);
                a.remove(a.size()-1);
            }
        }
    }
}
