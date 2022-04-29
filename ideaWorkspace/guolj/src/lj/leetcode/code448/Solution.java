package lj.leetcode.code448;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
public class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int len = nums.length;
        for(int i = 0 ; i < len ; i ++){
            int flow = i ;
            int fast = nums[i];
            while ( fast != 0 ){
                flow = fast;
                fast = nums[fast - 1];
                nums[flow - 1] = 0;
            }
        }
        for(int i = 0 ; i < len ; i ++){
            if(nums[i] != 0){
                res.add(i + 1);
            }
        }
        return res;
    }
}
