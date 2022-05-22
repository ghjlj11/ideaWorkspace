package lj.leetcode.code6076;

import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int minimumLines(int[][] stockPrices) {
        int len = stockPrices.length;
        if(len == 1){
            return 0;
        }
        sort(stockPrices, 0, len - 1);
        double x = stockPrices[1][1] - stockPrices[0][1];
        double y = stockPrices[1][0] - stockPrices[0][0];
        double cur = x / y;
        int sum = 1;
        for (int i = 2; i < len; i++) {
            x = stockPrices[i][1] - stockPrices[i - 1][1];
            y = stockPrices[i][0] - stockPrices[i - 1][0];
            if((x / y) != cur ){
                sum ++;
                cur = x / y;
            }
        }
        return sum;
    }
    private void sort(int[][] nums, int star, int end){
        if(star >= end){
            return;
        }
        int mid = (end - star)/2 + star;
        sort(nums, star, mid);
        sort(nums, mid + 1 , end);
        merge(nums, star, mid, mid + 1, end);
    }
    private void merge(int[][] nums, int s1, int e1, int s2, int e2){
        int len1 = e1 - e2 + 1, len2 = e2 - s2 + 1;
        int len = len1 + len2;
        int[][] res = new int[len][2];
        int i = s1, j = s2, k = 0;
        while (i <= e1 && j <= e2){
            if(nums[i][0] > nums[j][0]){
                res[k][0] = nums[j][0];
                res[k][1] = nums[j][1];
                j ++;
            }
            else {
                res[k][0] = nums[i][0];
                res[k][1] = nums[i][1];
                i ++;
            }
            k ++;
        }
        if(i > e1){
            while (j <= e2){
                res[k][0] = nums[j][0];
                res[k][1] = nums[j][1];
                j ++;
                k ++;
            }
        }
        else {
            while (i <= e1){
                res[k][0] = nums[i][0];
                res[k][1] = nums[i][1];
                i ++;
                k++;
            }
        }
        for(int x = 0 ; x < len ; x ++){
            nums[s1][0] = res[x][0];
            nums[s1++][1] = res[x][1];
        }
    }
}
