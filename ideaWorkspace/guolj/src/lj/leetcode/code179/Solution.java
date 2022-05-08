package lj.leetcode.code179;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 86187
 */
class Solution {
    public String largestNumber(int[] nums) {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String s1 = o1 + o2.toString();
                String s2 = o2 + o1.toString();
                return s1.compareTo(s2);
            }
        };
        Integer[] temp = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[i];
        }
        Arrays.sort(temp, comparator);
        StringBuilder res = new StringBuilder();
        for(int i = nums.length - 1; i >= 0 ; i --){
            if(nums[i] == 0){
                if(res.length() == 0 || res.charAt(0) != '0'){
                    res.append(temp[i]);
                }
            }
            else {
                res.append(temp[i]);
            }
        }
        return res.toString();
    }
}
