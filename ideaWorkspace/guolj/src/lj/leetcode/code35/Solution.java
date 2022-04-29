package lj.leetcode.code35;
//垃圾二分；
public class Solution {
    public int searchInsert(int[] nums, int target) {
        int l=0,r=nums.length-1;
        if(target<nums[l]){return 0;}
        if(target>nums[r]){return r+1;}
        while(l<=r){
            if(l+1==r){
                return target>nums[l]?r:l;
            }
            if(l==r){
                return target<=nums[l]?l:l+1;
            }
            int mid=(l+r)/2;
            if(target>nums[mid]){
                l=mid;
            }
            else if(target<nums[mid]){
                r=mid;
            }
            else {return mid;}
        }
        return 0;
    }
}
