package lj.leetcode.code560;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 */
public class Solution2 {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0 ;
        int count = 0;
        for (int num : nums) {
            sum += num;
            count += map.getOrDefault(sum - k , 0);
            int t = map.getOrDefault(sum, 0) + 1;
            map.put(sum, t);
        }
        return count;
    }
}
