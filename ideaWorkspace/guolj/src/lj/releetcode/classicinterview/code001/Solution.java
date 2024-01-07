package lj.releetcode.classicinterview.code001;

import java.util.Arrays;

/**
 * <p>
 * Description: 经典
 * <p>
 * 合并两个有序数组88
 *
 * @author guohuanjun1
 * @date 2023/12/20 11:14
 */
class Solution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m];
        System.arraycopy(nums1, 0, temp, 0, m);
        int i = 0, j = 0, k = 0;
        while (i < nums1.length) {
            if (j < m && k < n) {
                if ( temp[j] < nums2[k] ) {
                    nums1[i ++] = temp[j ++];
                }
                else {
                    nums1[i ++] = nums2[k ++];
                }
            }
            else if (j < m) {
                nums1[i ++] = temp[j ++];
            }
            else {
                nums1[i ++] = nums2[k ++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[20];
        int[] tmp = {2,4,6,8,10,12,14,16,18,20};
        int[] nums2 = {1,3,5,7,9,11,13,15,17,19};
        System.arraycopy(tmp, 0, nums1, 0, 10);
        merge(nums1, tmp.length, nums2, nums2.length);
        System.out.println(Arrays.toString(nums1));
    }
}
