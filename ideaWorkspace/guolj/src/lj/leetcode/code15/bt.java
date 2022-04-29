package lj.leetcode.code15;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
public class bt {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        int l,r;
        if(nums.length<3){return res;}
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i>0&&nums[i]==nums[i-1]){continue;}
            if(nums[i]>0){break;}
            l=i+1;
            r=nums.length-1;
            while(l<r){
                if(nums[l]+nums[i]+nums[r]==0){
                    while(r< nums.length-1&&nums[r]==nums[r+1]&&l<r){r--;}
                    while(l>i+1&&nums[l]==nums[l-1]&&l<r){l++;}
                    if(l<r&&nums[l]+nums[i]+nums[r]==0){
                        List<Integer> L1=new ArrayList<Integer>();
                        L1.add(nums[i]);
                        L1.add(nums[l]);
                        L1.add(nums[r]);
                        res.add(L1);
                        l++;
                        r--;}
                }
                while ((nums[l]+nums[i]+nums[r]<0)&&l<r){l++;}
                while (nums[l]+nums[i]+nums[r]>0&&l<r) {r--;}
            }
        }
        System.out.println(res);
        return res;
    }
}
