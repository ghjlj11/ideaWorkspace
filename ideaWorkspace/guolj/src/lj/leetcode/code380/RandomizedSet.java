package lj.leetcode.code380;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author 86187
 */
public class RandomizedSet {

    private int[] nums;
    private Map<Integer, Integer> map;
    private int index;
    private Random random;

    public RandomizedSet() {
        nums = new int[200001];
        map = new HashMap<>();
        random = new Random();
        index = -1;
    }

    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        nums[++index] = val;
        map.put(val, index);
        return true;
    }

    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        int idx = map.get(val);
        map.remove(val);
        if(idx != index){
            map.put(nums[index], idx);
            int temp = nums[idx];
            nums[idx] = nums[index];
            nums[index] = temp;
        }
        index--;
        return true;
    }

    public int getRandom() {
        return nums[random.nextInt(0,index + 1)];
    }
}
