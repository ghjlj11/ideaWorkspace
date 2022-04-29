package lj.leetcode.code78;
import java.util.*;
/**
 * @author ghj
 */
public class Main {
    public static void main(String[] args){
        int[] n={1,2,3};
        Solution A = new Solution();
        System.out.println(A.subsets(n));
    }
}
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        res.add(temp);
        adds(res,temp,nums,0,nums.length);
        return res;
    }
    private void adds(List<List<Integer>> res ,List<Integer> temp ,int[] nums, int times,int length){
        if(times==length){
            return;
        }
        for(int i=times;i<length;i++){
            temp.add(nums[i]);
            res.add(new ArrayList<Integer>(temp));
            adds(res,temp,nums,i+1,length);
            temp.remove(temp.size()-1);
        }
    }
}
