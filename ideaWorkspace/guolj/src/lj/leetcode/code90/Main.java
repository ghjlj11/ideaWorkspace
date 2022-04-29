package lj.leetcode.code90;
import java.util.*;
/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){
        int[] n = {0};
        Solution A = new Solution();
        System.out.println(A.subsetsWithDup(n));
    }
}
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        res.add(new ArrayList<Integer>());
        Arrays.sort(nums);
        dfs(res,temp,0,nums,nums.length);
        return res;
    }
    private void dfs(List<List<Integer>> res, List<Integer> temp, int k,int[] nums, int length){
        if( k >= length){
            return;
        }
        for(int i = k; i < length; i ++){
            while ( i >  k && i < length && nums[i] == nums[i-1]){
                i++;
            }
            if( i == length){
                break;
            }
            temp.add(nums[i]);
            res.add(new ArrayList<Integer>(temp));
            dfs(res,temp,i+1,nums,length);
            temp.remove(temp.size()-1);
        }
    }
}
