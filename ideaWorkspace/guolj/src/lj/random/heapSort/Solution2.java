package lj.random.heapSort;

import java.util.Arrays;

class Solution2 {
    public  void heap(int[] nums){
        built(nums);
        for(int i = nums.length - 1; i > 0  ; i --){
            swap(nums, 0, i);
            sink(nums, i, 0);
        }
    }
    private void built(int[] nums){
        for (int i = ( nums.length / 2 ) - 1; i >= 0 ; i --){
            sink(nums, nums.length, i);
        }
        System.out.println(Arrays.toString(nums));
    }
    private void sink(int[] nums, int end, int tar){

        int l = tar * 2 + 1, r = tar * 2 + 2, t = tar;
        if(l < end && nums[t] < nums[l]){
            t = l;
        }
        if(r < end && nums[t] < nums[r]){
            t = r;
        }
        if( t != tar){
            swap(nums, t, tar);
            sink(nums, end, t);
        }
    }
    private void swap(int[] nums, int l, int r){
        nums[l] = nums[l] + nums[r];
        nums[r] = nums[l] - nums[r];
        nums[l] = nums[l] - nums[r];
    }

    public static void main(String[] args) {
        Solution2 s = new Solution2();
        int[] k = {9,8,7,6,5,4,3};
        s.heap(k);
        System.out.println(Arrays.toString(k));
    }
}
