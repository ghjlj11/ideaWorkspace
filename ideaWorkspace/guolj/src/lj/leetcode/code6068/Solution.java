package lj.leetcode.code6068;

import java.util.*;

class Solution {
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {
        int len = tiles.length;
        Set<Integer> list = new HashSet<>();
        for (int[] tile : tiles) {
            for(int i = tile[0] ; i <= tile[1] ;i ++){
                list.add(i);
            }
        }
        int res = 0;
        for (int[] tile : tiles) {
            int star = tile[0];
            int sum = 0;
            for(int i = star ; i < star + carpetLen ; i ++){
                if(list.contains(i)){
                    sum ++;
                }
            }
            res = Math.max(res, sum);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution a = new Solution();

    }
}
