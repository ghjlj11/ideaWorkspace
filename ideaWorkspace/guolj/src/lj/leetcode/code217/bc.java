package lj.leetcode.code217;

import java.util.HashSet;
import java.util.Set;

public class bc {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> se=new HashSet<>();
        for(int i:nums){
            if(!se.add(i))
                return true;
        }
        return false;
    }
}
