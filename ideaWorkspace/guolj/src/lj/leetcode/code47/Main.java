package lj.leetcode.code47;
import java.util.*;
public class Main {
    public static void main(String[] args){
        int[] m={1,1,2};
        Solution S=new Solution();
        System.out.println(S.permuteUnique(m));
    }
}
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        List<Integer> a=new ArrayList<>();
        Arrays.sort(nums);
        int n=nums.length;
        boolean[] m=new boolean[n];
        Arrays.fill(m,true);
        pp(res,a,n,nums,0,m);
        return res;
    }
    private void pp(List<List<Integer>> res,List<Integer> a,int n,int[] nums,int x,boolean[] m){
        if(x==n){
            res.add(new ArrayList<Integer>(a));
            return;
        }
        int o=12;
        for(int i=0;i<n;i++){
            if(!m[i]||(i>0&&nums[i]==o)){
                    continue;
            }
            o=nums[i];
            a.add(nums[i]);
            m[i]=false;
            pp(res,a,n,nums,x+1,m);
            a.remove(a.size()-1);
            m[i]=true;
        }
    }
}
