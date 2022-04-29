package lj.leetcode.code26;

import java.util.Arrays;

public class bx {
    public int removeDuplicates(int[] nums) {
        int j=0;
        for(int i=0;i<nums.length;i++){
            if (nums[j]!=nums[i]){
                nums[j+1]=nums[i];
                j++;
            }
        }
        System.out.println(Arrays.toString(nums));
        return j+1;
    }
}
