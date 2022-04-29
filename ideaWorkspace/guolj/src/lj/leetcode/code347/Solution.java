package lj.leetcode.code347;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * @author 86187
 */
public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Arrays.sort(nums);
        int count = 1;
        int len = nums.length;
        for(int i = 0 , j = 1 ; j < len ; i ++ ,j ++){
            if(nums[j] != nums[i]){
                count ++;
            }
        }
        int[] dp1 = new int[count];
        int[] dp2 = new int[count];
        dp1[0] = nums[0];
        dp2[0] = 1;
        int n = 0 ;
        for(int i = 0 , j = 1 ; j < len ; i ++ ,j ++){
            if(nums[j] == nums[i]){
                dp2[n]++;
            }else {
                dp1[++n] = nums[j];
                dp2[n] = 1;
            }
        }
        quickSort(dp1,dp2,0,count - 1);
        for(int i = 0 ; i < k ; i ++){
            res[i] = dp1[i];
        }
        return res;
    }
    private void quickSort(int[] p1, int[] p2, int star, int end){
        int l = star;
        int r = end;
        while (l < r){
            while (l < r){
                if(p2[l] < p2[r]){
                    int temp = p2[l],temp2 = p1[l];
                    p1[l] = p1[r];
                    p1[r] = temp2;
                    p2[l] = p2[r];
                    p2[r] = temp;
                    break;
                }
                r --;
            }
            while (l < r){
                if(p2[l] < p2[r]){
                    int temp = p2[l], temp2 = p1[l];
                    p1[l] = p1[r];
                    p1[r] = temp2;
                    p2[l] = p2[r];
                    p2[r] = temp;
                    break;
                }
                l ++;
            }
        }
        if(l - 1 > star){
            quickSort(p1,p2,star,l - 1 );
        }
        if(r + 1 < end){
            quickSort(p1,p2,r + 1, end);
        }
    }
}
