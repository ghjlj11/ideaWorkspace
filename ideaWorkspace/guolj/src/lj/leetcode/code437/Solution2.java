package lj.leetcode.code437;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 86187
 */
public class Solution2 {
    Map<Long, Integer> map = new HashMap<>();
    public int pathSum(TreeNode root, int targetSum) {
        map.put(0L,1);
        return dfs(root, 0, 0 , targetSum);
    }
    private int dfs(TreeNode root, long sum,int res, int target){
        if(root == null){
            return res;
        }
        sum += root.val;
        res += map.getOrDefault(sum - target, 0 );
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        res = dfs(root.left, sum, res , target);
        res = dfs(root.right, sum, res , target);
        map.put(sum, map.get(sum) - 1);
        return res;
    }
}
