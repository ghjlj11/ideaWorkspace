package lj.leetcode.code118;
import java.util.ArrayList;
import java.util.List;
/**
 * @author 86187
 */
public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> temp = new ArrayList<Integer>();
        temp.add(1);
        res.add(new ArrayList<Integer>(temp));
        for(int i = 1; i < numRows; i ++){
            int b = temp.get(0);
            for(int j = 1; j < i; j ++){
                int a = b;
                b = temp.get(j);
                temp.set(j, a + b);
            }
            temp.add(1);
            res.add(new ArrayList<Integer>(temp));
        }
        return res;
    }
}
