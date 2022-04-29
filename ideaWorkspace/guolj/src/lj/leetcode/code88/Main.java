package lj.leetcode.code88;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){}
}
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int temp1 = m - 1,temp2 = n - 1, k = n + m - 1;
        while (temp2 >= 0 && temp1 >= 0){
            /*if(nums1[temp1] == nums2[temp2]){
                nums1[k--] = nums2[temp2--];
                nums1[k--] = nums1[temp1--];
            }
            else if(nums1[temp1] < nums2[temp2]){
                nums1[k--] = nums2[temp2--];
            }
            else{
                nums1[k--] = nums1[temp1--];
            }*/
            nums1[k--] = nums1[temp1] > nums2[temp2]? nums1[temp1--] : nums2[temp2--];
        }
        while (temp2 >= 0){
            nums1[k--] = nums2[temp2--];
        }
    }
}
