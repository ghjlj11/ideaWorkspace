package lj.leetcode.code89;
import java.util.*;
/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){
        int n = 3;
        Solution A = new Solution();
        System.out.println(A.Code(n));
    }
}
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        int base = 1;
        res.add(0);
        for(int i = 0; i < n; i ++){
            int k = 1;
            int x = res.size();
            while (k <= x){
                int temp = res.get(x-k) + base;
                res.add(temp);
                k ++;
            }
            base = base * 2;
        }
        return res;
    }
    public List<Integer> Code(int n){
        List<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i < 1 << n; i ++){
            res.add((i >> 1) ^ i);
        }
        return res;
    }
}
