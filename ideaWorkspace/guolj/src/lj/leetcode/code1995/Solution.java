package lj.leetcode.code1995;

public class Solution {
    public int countQuadruplets(int[] nums) {
        int x=0,i,j,k,m;
        for(i=0;i<nums.length;i++){
            for(j=0;j<i;j++){
                for(k=0;k<j;k++){
                    for(m=0;m<k;m++){
                        if(nums[i]==nums[j]+nums[k]+nums[m])
                        {System.out.println(nums[i]+" "+nums[j]+" "+nums[k]+" "+nums[m]);x++;}
                    }
                }
            }
        }
        return x;
    }
}