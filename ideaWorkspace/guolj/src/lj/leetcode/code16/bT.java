package lj.leetcode.code16;

import java.util.Arrays;

public class bT {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res=3000,n=nums.length,sum=0;
        for(int i=0;i<n-2;i++){
            int l=i+1,r=n-1,x=0;
            while(l<r){
                int tt=nums[i]+nums[l]+nums[r];
                if(res>Math.abs(tt-target)){
                    res=Math.abs(tt-target);
                    sum=tt;
                }
                if(tt<target){
                    l++;
                }else {
                    r--;
                }
            }
        }
        return sum;
    }
}
