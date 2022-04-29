package lj.leetcode.code35;
//牛逼二分；
public class Solution2 {
    public int searchInsert(int[] nums, int target) {
        int l=0,r=nums.length-1;
        while(l<=r){
            int mid=(r-l)/2+l;
            if(target<=nums[mid]){
                r=mid-1;
            }
            else {
                l=mid+1;
            }
        }
        return l;
    }
}
