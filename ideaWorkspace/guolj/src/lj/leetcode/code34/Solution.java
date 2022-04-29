package lj.leetcode.code34;

public class Solution {
    public int[] searchRange(int[] nums, int target) {
        int l=0,r=nums.length-1;
        if(nums.length==1){return nums[0]==target?(new int[]{0,0}):(new int[]{-1,-1});}
        while(l<=r){
            int mid=(l+r)/2;
            if(nums[mid]==target){
                int x=mid,y=nums.length-1;
                r=mid;
                l=0;
                while (l<=r){
                    int mid1=(l+r)/2;
                    if(nums[mid1]<target){l=mid1+1;}
                    else {r=mid1-1;}
                }
                while(x<=y){
                    int mid2=(x+y)/2;
                    if(nums[mid2]>target){y=mid2-1;}
                    else{x=mid2+1;}
                }
                return new int[]{r+1,y};
            }
            else if(nums[mid]<target){l=mid+1;}
            else if(nums[mid]>target){r=mid-1;}
        }
        return new int[]{-1,-1};
    }
}
