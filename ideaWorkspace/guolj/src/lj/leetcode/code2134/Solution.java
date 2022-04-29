package lj.leetcode.code2134;

public class Solution {
    public int minSwaps(int[] nums) {
        int n=nums.length,res=n/2;
        int onecnt=0,zerocnt=0;
        for(int num:nums){
            if(num==1)onecnt++;
        }
        for(int i=0;i<onecnt+n-1;i++){
            if(nums[i%n]==0){
                zerocnt++;
            }
            if(i>=onecnt-1){
                if(i>onecnt-1&&nums[i-onecnt]==0)
                zerocnt--;
                res=Math.min(res,zerocnt);
            }
        }
        return res;
    }
}
