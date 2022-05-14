package lj.leetcode.code6067;

/**
 * @author 86187
 */
class Solution {
    public int waysToSplitArray(int[] nums) {
        int res = 0;
        int len = nums.length;
        long[] pre = new long[len];
        long[] suf = new long[len];
        for (int num : nums) {
            suf[0] += num;
        }
        suf[0] -= nums[0];
        pre[0] = nums[0];
        if(pre[0] >= suf[0]){
            res ++;
        }
        for(int i = 1 ; i < len - 1 ; i ++){
            pre[i] = pre[i - 1] + nums[i];
            suf[i] = suf[i - 1] - nums[i];
            if(pre[i] >= suf[i]){
                res ++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution a = new Solution();
        int i = a.waysToSplitArray(new int[]{2,3,1,0});
        System.out.println(i);
    }
}
