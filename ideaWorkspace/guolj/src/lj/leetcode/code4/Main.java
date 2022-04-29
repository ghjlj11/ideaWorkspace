package lj.leetcode.code4;

public class Main {
    public static void main(String[] args){
        int[] nums1={1,2};
        int[] nums2={3,4};
        Solution A=new Solution();
        System.out.println(A.findMedianSortedArrays(nums1,nums2));
    }
}
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1=nums1.length,len2=nums2.length;
        int sum=len1+len2;
        if(sum%2==1){
            int hf=sum/2+1;
            return oo(nums1,nums2,0,0,hf);
        }else {
            int hf1=sum/2;
            int hf2=sum/2+1;
            return (oo(nums1,nums2,0,0,hf1)+oo(nums1,nums2,0,0,hf2))/2.0;
        }
    }
    private int pp(int[] nums1,int[] nums2,int k){
        int len1=nums1.length,len2=nums2.length;
        int index1=0,index2=0;
        while (true){
            if(len1==index1){
                return nums2[index2+k-1];
            }
            if(len2==index2){
                return nums1[index1+k-1];
            }
            if(k==1){
                return Math.min(nums1[index1],nums2[index2]);
            }
            int harf=k/2;
            int index11=Math.min(len1,index1+harf)-1;
            int index22=Math.min(len2,index2+harf)-1;
            int n1=nums1[index11],n2=nums2[index22];
            if(n1<=n2){
                k-=(index11-index1+1);
                index1=index11+1;
            }else{
                k-=(index22-index2+1);
                index2=index22+1;
            }
        }
    }
    private int oo(int[] nums1,int[] nums2,int index1,int index2,int k){
        if(nums1.length==index1){
            return nums2[index2+k-1];
        }
        if(nums2.length==index2){
            return nums1[index1+k-1];
        }
        if(k==1){
            return Math.min(nums1[index1],nums2[index2]);
        }
        int harf=k/2;
        int index11=Math.min(nums1.length,index1+harf)-1;
        int index22=Math.min(nums2.length,index2+harf)-1;
        if(nums1[index11]<=nums2[index22]){
            return oo(nums1,nums2,index11+1,index2,k-(index11-index1+1));
        }else{
            return oo(nums1,nums2,index1,index22+1,k-(index22-index2+1));
        }
    }
}