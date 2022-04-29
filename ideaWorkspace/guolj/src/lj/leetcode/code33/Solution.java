package lj.leetcode.code33;

/**
 * @author 86187
 */
public class Solution {
    public int search(int[] nums, int target) {
        int n=nums.length,l=0,r=n-1,mid;
        if(n==0){return -1;}
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        while(l<=r){
            mid=(r+l)/2;
            if(nums[mid]==target){return mid;}
            if(nums[0]<=nums[mid]){
                if(nums[0]<=target&&target<nums[mid]){
                    r=mid-1;
                }
                else{
                    l=mid+1;
                }
            }
            else{
                if(nums[mid]<target&&target<=nums[n-1]){
                    l=mid+1;
                }
                else{
                    r=mid-1;
                }
            }
        }
        return -1;
    }
}
