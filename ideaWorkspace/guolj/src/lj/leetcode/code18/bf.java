package lj.leetcode.code18;
import java.util.*;

public class bf {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        if(nums==null||nums.length<4){
            return res;
        }
        Set<List<Integer>> k=new HashSet<List<Integer>>();
        Arrays.sort(nums);
        int n=nums.length,l,r;
        for(int i=0;i<n-3;i++){
            for(int j=i+1;j<n-2;j++){
                l=j+1;
                r=n-1;
                while(l<r){
                    int sum=nums[i]+nums[j]+nums[l]+nums[r];
                    if(sum==target){
                        List<Integer> t=new ArrayList<Integer>();
                        t.add(nums[i]);
                        t.add(nums[j]);
                        t.add(nums[l]);
                        t.add(nums[r]);
                        if(!k.contains(t)){
                            res.add(t);
                            k.add(t);
                        }
                        l++;
                        r--;
                    }
                    else if(sum<target){
                        l++;
                    }else {
                        r--;
                    }
                }
            }
        }
        return res;
    }
}
