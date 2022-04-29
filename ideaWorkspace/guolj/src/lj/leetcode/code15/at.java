package lj.leetcode.code15;

import java.util.List;

public class at {
    public static void main(String[] args){
        int[] n={-2,-1,0,2,3};
        bt t=new bt();
        List<List<Integer>> e=t.threeSum(n);
        System.out.println(e);
    }
}
