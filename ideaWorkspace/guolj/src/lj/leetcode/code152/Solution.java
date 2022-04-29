package lj.leetcode.code152;
import java.util.List;
import java.util.ArrayList;
/**
 * @author 86187
 */
public class Solution {
    public int maxProduct(int[] nums) {
        int length = nums.length;
        if(length == 1){
            return nums[0];
        }
        int[] dp = new int[length];
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(nums[0]);
        dp[0] = nums[0];
        int max = dp[0];
        for(int i = 1; i < length; i ++){
            if(nums[i] == 1){
                max = Math.max(max, 1);
                dp[i] = max;
                temp.add(1);
                continue;
            }
            for(int j = 0; j < i; j ++){
                int x = temp.get(j) * nums[i];
                if(max < x){
                    max = x;
                }
                temp.set(j, x);
            }
            if(max < nums[i]){
                max = nums[i];
            }
            temp.add(nums[i]);
            dp[i] = max;
        }
        return dp[length - 1];
    }
}
