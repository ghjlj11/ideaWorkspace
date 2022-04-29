package lj.leetcode.code11;
public class hh {
    public int maxArea(int[] height) {
        int i=0,j=height.length-1,max=0,k,x;
        while(i<j){
            k=Math.min(height[j],height[i]);
            x=k*(j-i);
            max=Math.max(max,x);
            if(height[i]<height[j]){
                ++i;
            }
            else{
                --j;
            }
        }
        return max;
   }
}