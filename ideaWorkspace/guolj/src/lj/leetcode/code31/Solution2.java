package lj.leetcode.code31;

public class Solution2 {
    public void nextPermutation(int[] nums) {
        int n=nums.length,i=n-2;
        while(i>=0&&nums[i]>=nums[i+1]){
            i--;
        }
        if(i>=0){
            int j=n-1;
            while(j>i&&nums[i]>=nums[j]){
                j--;
            }
            swap(nums,i,j);
        }
        reverse(nums,i+1);
    }
    private void swap(int[] a,int b,int c){
        int t=a[b];
        a[b]=a[c];
        a[c]=t;
    }
    private void reverse(int[] a,int c){
        int l=c,r=a.length-1;
        while(l<r){
            swap(a,l,r);
            l++;
            r--;
        }
    }
}
