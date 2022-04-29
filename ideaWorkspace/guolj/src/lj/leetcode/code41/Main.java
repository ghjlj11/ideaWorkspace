package lj.leetcode.code41;

class Main {
    public static void main(String[] args){
        int[] n = {1,1};
        Solution A = new Solution();
        System.out.println(A.first(n));
    }
}
class Solution {
    public int firstMissingPositive(int[] nums) {
        int flow = 0;
        int length = nums.length;
        for(int i = 0; i < length; i++){
            while (nums[i] > 0 && nums[i] <= length ){
                if(nums[i] - 1 == i || nums[i] == nums[nums[i] - 1]){
                    break;
                }
                int temp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[temp - 1] = temp;
            }
        }
        while ( flow < length && nums[flow] == flow + 1){
            flow++;
        }
        return flow + 1;
    }
    public int first(int[] nums) {
        int length = nums.length;
        for(int i = 0; i < length; i ++){
            if(nums[i] <= 0){
                nums[i] = length+1;
            }
        }
        for(int i = 0; i < length ; i ++){
            if(Math.abs(nums[i]) <= length){
                int temp = Math.abs(nums[i]) - 1;
                nums[temp] = nums[temp] < 0 ? nums[temp] : -nums[temp];
            }
        }
        for(int i = 0; i < length; i ++){
            if(nums[i] > 0){
                return i + 1;
            }
        }
        return length + 1;
    }
}
