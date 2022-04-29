package lj.leetcode.code46;
import java.util.*;
public class Main {
    public static void main(String[] args){
        int[] nums={1,2,3};
        Solution A=new Solution();
        System.out.println(A.permute(nums));
    }
}
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans=new ArrayList<List<Integer>>();
        List<Integer> a=new ArrayList<Integer>();
        int x=nums.length;
        pp(nums,ans,x,a);
        return ans;
    }
    private void pp(int[] nums,List<List<Integer>> ans,int x,List<Integer> a){
        if(a.size()==x){
            ans.add(new ArrayList<Integer>(a));
            return;
        }
        for(int i=0;i<x;i++){
            if(a.contains(nums[i])){continue;}
            a.add(nums[i]);
            pp(nums,ans,x,a);
            a.remove(Integer.valueOf(nums[i]));
        }
    }
}
