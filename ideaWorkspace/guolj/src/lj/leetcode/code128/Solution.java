package lj.leetcode.code128;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 86187
 */
public class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        int longest = 0;
        int len = 1;
        for(int num : set){
            if(!set.contains(num - 1)){
                int curNum = num;
                while (set.contains(curNum + 1)){
                    len ++;
                    curNum ++;
                }
                longest = Math.max(longest, len);
                len = 1;
            }
        }
        return longest;
    }
}
