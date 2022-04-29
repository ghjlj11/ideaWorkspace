package lj.leetcode.offer64;

/**
 * @author 86187
 */
public class Solution {
    public int sumNums(int n) {
        boolean b = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }
    public int sum(int n){
        return n > 0 ? n + sum(n - 1) : 0;
    }
}
