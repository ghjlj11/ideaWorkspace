package lj.leetcode.code137;

/**
 * @author 86187
 */
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0 ;
        for(int i = 0 ; i < 32 ; i ++){
            int sum = 0 ;
            for (int num : nums) {
                int t = ( num >> i ) & 1;
                sum += t ;
            }
            if(sum % 3 != 0){
                res |= 1 << i;
            }
        }
        return res;
    }
}
