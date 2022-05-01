package lj.leetcode.code189;

/**
 * @author 86187
 */
class Solution {
    public void rotate(int[] nums, int k) {
        if(k == 0){
            return;
        }
        int len = nums.length;
        k = k % len;
        int f = te(len, k);
        for(int i = 0 ; i < f ; i ++){
            int  x , y = i, temp = nums[i], time = 0;
            while (time < 2){
                temp = nums[y] ^ temp;
                nums[y] = temp ^ nums[y];
                temp = nums[y] ^ temp ;
                x = y ;
                if( x== i){
                    time++;
                }
                y = (x + k) % len ;
            }
        }
    }
    private int te(int x , int y){
        return y > 0 ? te(y, x % y) : x;
    }
}
