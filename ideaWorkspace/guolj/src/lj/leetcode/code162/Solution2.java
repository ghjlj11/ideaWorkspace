package lj.leetcode.code162;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

class Solution2 {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length -  1;
        while (l < r){
            int mid = (l + r) / 2;
            if(nums[mid] > nums[mid + 1]){
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return r;
    }
}
