package lj.leetcode.code347;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author 86187
 */
public class Solution2 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> que = new PriorityQueue<>((o1,o2) -> o1[1] - o2[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            int temp = que.size() != 0 ? que.peek()[1] : 0;
            if(que.size() == k){
                if(value > temp){
                    que.poll();
                    que.add(new int[]{key, value});
                }
            }
            else {
                que.add(new int[]{key, value});
            }
        }
        for(int i = 0 ; i < k ; i ++){
            res[i] = que.poll()[0];
        }
        return res;
    }
}
