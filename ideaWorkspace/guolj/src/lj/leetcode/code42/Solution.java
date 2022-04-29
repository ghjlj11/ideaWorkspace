package lj.leetcode.code42;

/**
 * @author 86187
 */
public class Solution {
    public int trap(int[] height) {
        int n=height.length,l=0,r=n-1,sum=0,max1=0,max2=0;
        while (l<=r){
            if(max1<max2){
                if(height[l]>max1){
                    max1=height[l];
                }
                else{
                    sum+=max1-height[l];
                }
                l++;
            }
            else{
                if(height[r]>max2){
                    max2=height[r];
                }
                else {
                    sum+=max2-height[r];
                }
                r--;
            }
        }
        return sum;
    }
    public int trap2(int[] height){
        int len = height.length;
        int[] leftHeight = new int[len];
        int[] rightHeight = new int[len];
        int leftMax = height[0];
        for(int  i = 0; i < len; i ++){
            leftMax = Math.max(leftMax, height[i]);
            leftHeight[i] = leftMax;
        }
        int rightMax = height[len - 1];
        for(int i = len - 1; i >= 0 ; i --){
            rightMax = Math.max(rightMax, rightHeight[i]);
            rightHeight[i] = rightMax;
        }
        int sum = 0;
        for(int i = 0; i < len; i ++){
            int min = Math.min(leftHeight[i], rightHeight[i]);
            sum += min - height[i];
        }
        return sum;
    }
}
