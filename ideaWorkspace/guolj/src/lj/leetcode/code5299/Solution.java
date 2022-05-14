package lj.leetcode.code5299;

/**
 * @author 86187
 */
class Solution {
    public int divisorSubstrings(int num, int k) {
        String s = String.valueOf(num);
        int len = s.length();
        int res = 0;
        for(int i = 0 ; i <= len - k; i ++){
            String s1 = s.substring(i, i + k);
            int t = Integer.parseInt(s1);
            if(t != 0 && num % t == 0){
                res ++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution a = new Solution();
        int i = a.divisorSubstrings(430043, 2);
        System.out.println(i);
    }
}
