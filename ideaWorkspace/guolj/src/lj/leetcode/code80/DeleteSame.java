package lj.leetcode.code80;

/**
 * @author 86187
 */
public class DeleteSame {
    public static void main(String[] args){
        int[] nums = {0,0,1,1,1,1,2,3,3};
        Solution A = new Solution();
        System.out.println(A.removeDuplicates(nums));
    }
}
class Solution {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        int j = 1, k = 1, i = 1;
        for(i = 1; i < length; i ++,j ++){
            if(nums[i] == nums[i-1]){
                k ++;
                if(k >= 3){
                    j--;
                }
            }
            else{
                k = 1;
            }
            nums[j] = nums[i];
        }
        return j;
    }
}
