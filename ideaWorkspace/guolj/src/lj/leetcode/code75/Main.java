package lj.leetcode.code75;

/**
 * @author ghj
 */
public class Main {
    public static void main(String[] args){
        int[] n={2,0,2,1,1,0};
        Solution A=new Solution();
        A.sortColors(n);
        for(int i=0;i<n.length;i++){
            System.out.println(n[i]);
        }
    }
}
class Solution {
    public void sortColors(int[] nums) {
        int left = 0, right  = nums.length - 1, i = 0 ;
        while (i <= right){
            if(nums[i] == 0){
                swap(nums, i++,left++);
            }
            else if(nums[i] == 2){
                swap(nums,i,right--);
            }
            else {
                i++;
            }
        }
    }
    private void swap(int[] nums , int l,int r){
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}
