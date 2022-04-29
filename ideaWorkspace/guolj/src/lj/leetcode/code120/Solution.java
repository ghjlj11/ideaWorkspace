package lj.leetcode.code120;
import java.util.List;
/**
 * @author 86187
 */
public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int length = triangle.size();
        int[] dp = new int[length];
        dp[0] = triangle.get(0).get(0);
        for(int i = 1; i < length; i ++){
            int p2 =dp[0];
            dp[0] += triangle.get(i).get(0);
            for(int j = 1; j < i ; j ++){
                int p1 = p2;
                p2 = dp[j];
                int min = Math.min(p1,p2);
                dp[j] = triangle.get(i).get(j) + min;
            }
            dp[i] = p2 + triangle.get(i).get(i);
        }
        int min = dp[0];
        for(int i = 1; i < length; i ++){
            min = Math.min(min, dp[i]);
        }
        return min;
    }
}
