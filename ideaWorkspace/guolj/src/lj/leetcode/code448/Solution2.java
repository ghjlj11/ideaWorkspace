package lj.leetcode.code448;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author 86187
 */
public class Solution2 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        for (int num : nums) {
            int k = ( num - 1 ) % len;
            nums[k] += len;
        }
        for(int i = 0 ; i < len ; i ++ ){
            if(nums[i] <= len){
                res.add(i + 1);
            }
        }

        return res;
    }
}
