package lj.random.heapSort;

import java.util.Arrays;

/**
 * @author 86187
 */
public class Solution {
    public static void main(String[] args){
        int[] n = {9,8,7,6,5,4,3,2,1};
        Solution a = new Solution();
        a.heapSort(n);
        System.out.println(Arrays.toString(n));
    }

    private void heapSort(int[] nums){
        int len = nums.length;
        builtHeap(nums,len);
        for(int i = len - 1; i > 0 ; i --){
            swap(nums, 0, i);
            sink(nums, 0, i);
        }
    }
    private void builtHeap(int[] arr, int len){
        for(int i =( len / 2 ) - 1 ; i >= 0 ; i --){
            sink(arr, i, len);
        }
    }
    private void sink(int[] arr, int i, int len){
        int target = i ;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if(l < len && arr[l] > arr[target]){
            target = l ;
        }
        if(r < len && arr[r] > arr[target]){
            target = r ;
        }
        if(target != i){
            swap(arr, target, i);
            sink(arr, target, len);
        }
    }
    private void swap(int[] arr, int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
}
