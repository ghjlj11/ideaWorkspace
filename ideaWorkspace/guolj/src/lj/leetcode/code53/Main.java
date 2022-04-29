package lj.leetcode.code53;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){
        int[] n={-2,1,-3,4,-1,2,1,-5,4};
        Solution A=new Solution();
        System.out.println(A.aa(n));
    }
}
class Solution {
    public int maxSubArray(int[] nums) {
        int res=Integer.MIN_VALUE;
        int sum=0,i=0;
        while (i<nums.length){
            if(sum<0){
                sum=nums[i];
            }
            else {
                sum=sum+nums[i];
            }
            res=Math.max(res,sum);
            i++;
        }
        return res;
    }
    public int aa(int[] nums){
        int res=nums[0],max=0;
        for(int n:nums){
            max=Math.max(n,max+n);
            res=Math.max(res,max);
        }
        return res;
    }
}
