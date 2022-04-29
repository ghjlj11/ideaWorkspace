package lj.leetcode.code93;
import java.util.*;
/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){
        String s = "25525511135";
        Solution A = new Solution();
        System.out.println(A.restoreIpAddresses(s));
    }
}
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        int length = s.length();
        if(length < 4 || length > 12){
            return res;
        }
        StringBuffer ans = new StringBuffer();
        dfs(res,s,0,length,0,ans);
        return res;
    }
    private void dfs(List<String> res, String s, int k, int length, int nums,StringBuffer ans){
        if( nums == 3){
            String s2 = s.substring(k,length);
            int num = Integer.parseInt(s2);
            if(s2.length() == 1){
                ans.append(".");
                ans.append(s2);
                res.add(new String(String.valueOf(ans)));
                ans.delete(ans.length() - 2,ans.length());
            }
            else if(s2.length() > 1 && s2.charAt(0) != '0' && num <= 255){
                ans.append(".");
                ans.append(s2);
                res.add(new String(String.valueOf(ans)));
                ans.delete(ans.length() - s2.length() - 1,ans.length());
            }
            return;
        }
        else {
            for(int i = k + 1 ; i < k + 4 && i < length; i ++){
                String s1 = s.substring(k , i);
                int num = Integer.parseInt(s1);
                int length2 = s1.length();
                if(length2 == 1){
                    if(nums != 0){
                        ans.append(".");
                        length2 ++;
                    }
                    ans.append(s1);
                    dfs(res,s,i,length,nums + 1,ans);
                    ans.delete(ans.length() - length2,ans.length());
                }
                else if(length2 > 1 && s1.charAt(0) != '0' && num <= 255){
                    if(nums != 0){
                        ans.append(".");
                        length2++;
                    }
                    ans.append(s1);
                    dfs(res,s,i,length,nums + 1,ans);
                    ans.delete(ans.length() - length2,ans.length());
                }
            }
        }
    }
}