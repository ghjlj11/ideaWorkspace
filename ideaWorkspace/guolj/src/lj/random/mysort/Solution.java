package lj.random.mysort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 86187
 */
public class Solution {
    public int longestConsecutive(int[] nums) {
        int len = nums.length;
        if(len == 0){
            return 0;
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < 10; i ++){
            res.add(new ArrayList<>());
        }
        int max = nums[0];
        for(int i = 1 ; i < len; i ++){
            if(max < nums[i]){
                max = nums[i];
            }
        }
        int maxTime = 0;
        while (max > 0){
            maxTime ++;
            max /= 10;
        }
        for(int i = 1; i <= maxTime; i ++){
            for(int j = 0; j < len; j ++){
                int road = (int)Math.pow(10, i);
                int road2 = road / 10;
                int k = nums[j] / road;
                int temp = ( nums[j] - k * road ) / road2;
                res.get(temp).add(nums[j]);
            }
            int k = 0;
            for(int l = 0 ;l < 10; l ++){
                List<Integer> temp2 = res.get(l);
                int size = temp2.size();
                for(int j = 0; j < size; j ++){
                    nums[k] = temp2.remove(0);
                    k++;
                }
            }
        }
        int longest = 1;
        int maxLong = 1;
        for(int i = 1 ;i < len ; i ++){
            if(nums[i] == nums[i - 1] + 1){
                longest ++;
            }
            else {
                maxLong = Math.max(maxLong, longest);
                longest = 1;
            }
        }
        maxLong = Math.max(maxLong, longest);
        return maxLong;
    }
}
