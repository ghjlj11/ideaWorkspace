package lj.leetcode.code6065;

class Solution {
    public int largestCombination(int[] candidates) {
        return dfs(candidates, 1, 1, candidates[0]);
    }
    private int dfs(int[] can, int pose, int len, int t){
        int l = can.length, res = len ;
        if(pose >= l){
            return len;
        }
        for(int i = pose ; i < l ; i ++){
            int x = t & can[i];
            if(x > 0){
                int max = dfs(can, i + 1, len + 1, x );
                res = Math.max(res, max);
            }
        }
        return res;
    }
}
