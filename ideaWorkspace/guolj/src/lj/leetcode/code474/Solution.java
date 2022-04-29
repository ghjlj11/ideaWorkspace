package lj.leetcode.code474;

/**
 * @author 86187
 */
public class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        int len = strs.length;
        int[][] dp2 = new int[m + 1][n + 1];
        for (String str : strs) {
            int length = str.length();
            int x = 0, y;
            for (int l = 0; l < length; l++) {
                if (str.charAt(l) == '0') {
                    x++;
                }
            }
            y = length - x;
            for (int j = m; j >= 0; j--) {
                for (int k = n; k >= 0; k--) {
                    if (j >= x && k >= y) {
                        int temp = 1 + dp2[j - x][k - y];
                        dp2[j][k] = Math.max(dp2[j][k], temp);
                    }
                }
            }
        }
        return dp2[m][n];
    }
}
