package lj.leetcode.code215;

/**
 * @author 86187
 */
public class Solution2 {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        builtHeap(nums, len);
        for(int i = len -1 ; i > len - k ; i --){
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;
            sink(nums, 0, i );
        }
        return nums[0];
    }
    private void builtHeap(int[] arr, int len){
        for(int i = ( len / 2 ) - 1 ; i >= 0 ; i --){
            sink(arr, i, len);
        }
    }
    private void sink(int[] arr, int i, int len){
        int l = i * 2 + 1;
        int r = i * 2 + 2;
        int target = i;
        if(l < len && arr[target] < arr[l]){
            target = l ;
        }
        if(r < len && arr[target] < arr[r]){
            target = r ;
        }
        if(target != i){
            int temp = arr[target];
            arr[target] = arr[i];
            arr[i] = temp;
            sink(arr, target, len);
        }
    }
}
