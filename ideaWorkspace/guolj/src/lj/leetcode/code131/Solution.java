package lj.leetcode.code131;
import java.util.List;
import java.util.ArrayList;
/**
 * @author 86187
 */
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> temp = new ArrayList<String>();
        dfs(res, temp, s,0, s.length());
        return res;
    }
    private void dfs(List<List<String>> res, List<String> temp , String s,  int star, int len){
        if(star == len){
            res.add(new ArrayList<String>(temp));
            return;
        }
        for(int i = star + 1; i <= len; i ++){
            if(isH(s, star, i)){
                temp.add(s.substring(star, i));
                dfs(res, temp, s, i, len);
                temp.remove(temp.size() - 1);
            }
        }
    }
    private boolean isH(String s, int l,int r){
        for(int i = l ; i < r; i ++){
            if(s.charAt(i) != s.charAt(-- r)){
                return false;
            }
        }
        return true;
    }
}
