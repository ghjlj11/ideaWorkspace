package lj.leetcode.code134;

/**
 * @author 86187
 */
public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int res = 0;
        int index = 0;
        int sum = 0 ;
        while (index < gas.length){
            for(int i = index ; i < gas.length ; i ++){
                if(gas[i] > cost[i]){
                    index = i;
                    break;
                }
                sum += gas[i] - cost[i];
            }
            res = index;
            int gasSum = 0;
            int costSum = 0;
            while (gasSum >= costSum && index < gas.length){
                sum += gas[index] - cost[index];
                gasSum += gas[index];
                costSum += cost[index];
                index ++;
            }
        }
        return sum < 0 ? -1 : res;
    }
}
