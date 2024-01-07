package lj.releetcode.classicinterview.code002;

import java.util.Arrays;

/**
 * <p>
 * Description: 移除元素27
 * <p>
 *
 * @author guohuanjun
 * @date 2024/1/2  15:48
 */
class Solution {
    public static int removeElement(int[] nums, int val) {
        int idx1 = 0, idx2 = nums.length - 1, result = 0;
        while (idx1 < idx2) {
            if (nums[idx1] == val) {
                swapArray(nums, idx1, idx2);
                idx2 --;
            }
            else {
                idx1 ++;
            }
        }
        while (nums.length > 0 && result < nums.length && nums[result] != val) {
            result ++;
        }
        return result;
    }

    private static void swapArray (int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    public static void main(String[] args) {
        int[] num = {2,3};
        int var = 2;
        System.out.println(removeElement(num, var));
        System.out.println(Arrays.toString(num));
    }
}