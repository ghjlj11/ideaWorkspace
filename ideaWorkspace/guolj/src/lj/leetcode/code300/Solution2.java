package lj.leetcode.code300;

/**
 * @author 86187
 */
public class Solution2 {
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        int count = 0;
        dp[0] = Integer.MIN_VALUE;
        for (int k : nums) {
            if (k > dp[count]) {
                dp[++count] = k;
            } else if (k < dp[count]) {
                int l = 0, r = count - 1, pos = -1;
                while (l <= r) {
                    int mid = (l + r) / 2;
                    if (dp[mid] < k) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                if (pos != -1) {
                    dp[pos + 1] = k;
                }
            }
        }
        return count;
    }
}
