package lj.releetcode.classicinterview.code001;

import java.util.Arrays;

/**
 * <p>
 * Description: 经典
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/12/20 11:14
 */
class Solution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] temp = new int[m];
        int length = nums1.length;
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        else if (n == 0) {
            return;
        }
        System.arraycopy(nums1, 0, temp, 0, m);
        int indexT = 0, index2 = 0, index1 = 0, k1 = temp[0], k2 = nums2[0];
        while (index1 < length) {
            while (k1 < k2 && index1 < length) {
                nums1[index1++] = k1;
                k1 = ++ indexT < m ? temp[indexT] : k1;
            }
            while (k1 >= k2 && index1 < length) {
                nums1[index1++] = k2;
                k2 = ++ index2 < n ? nums2[index2] : k2;
            }
        }
        nums1[length - 1] = Math.max(k1, k2);
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
