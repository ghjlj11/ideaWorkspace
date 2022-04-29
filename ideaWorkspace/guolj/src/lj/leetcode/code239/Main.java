package lj.leetcode.code239;

import java.util.Arrays;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		int[] n = {1,2,3,4,5,6,7,8,9};
		Solution a  = new Solution();
		int[] m = a.maxSlidingWindow(n, 3);
		System.out.println(Arrays.toString(m));
	}
}
