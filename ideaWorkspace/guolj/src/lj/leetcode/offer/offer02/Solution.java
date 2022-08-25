package lj.leetcode.offer.offer02;

/**
 * @author 86187
 * 二进制加法
 */
public class Solution {
    public String addBinary(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();
        char[] ac = a.toCharArray();
        char[] bc = b.toCharArray();
        StringBuilder res = new StringBuilder();
        int temp = 0;
        for (int i = len1 - 1 , j = len2 - 1; i >= 0 || j >= 0 || temp == 1; i--, j --) {
            int x = 0;
            int y = 0;
            if(i >= 0){
                x = Integer.parseInt(String.valueOf(ac[i]));
            }
            if(j >= 0){
                y = Integer.parseInt(String.valueOf(bc[j]));
            }
            int sum = x + y + temp;
            if(sum % 2 == 0){
                res.append("0");
            }
            else{
                res.append("1");
            }
            if(sum >= 2){
                temp = 1;
            }
            else {
                temp = 0;
            }
        }
        return res.reverse().toString();
    }
}
