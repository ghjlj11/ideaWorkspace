package lj.leetcode.code238;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		int[] nums = {-1,1,0,-3,3};
		Solution a = new Solution();
		int[] k = a.productExceptSelf(nums);
		System.out.println(Arrays.toString(k));
	}
}
