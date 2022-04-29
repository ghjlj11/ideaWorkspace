package lj.leetcode.code119;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
/**
 * @author 86187
 */
public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> res2 = new ArrayList<Integer>();
        res2.add(1);
        for(int i = 1; i <= rowIndex; i ++){
            int p2 = res2.get(0);
            int length = res2.size();
            for(int j = 1; j < length; j++){
                int p1 = p2;
                p2 = res2.get(j);
                res2.set(j,p1 + p2);
            }
            res2.add(1);
        }
        return res2;
    }
}
