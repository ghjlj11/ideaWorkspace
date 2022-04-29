package lj.leetcode.code49;
import java.util.*;
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,ArrayList<String>> map=new HashMap<String,ArrayList<String>>();
        for(String s:strs){
            char[] c=s.toCharArray();
            Arrays.sort(c);
            String key=String.valueOf(c);
            if(!map.containsKey(key)){
                ArrayList<String> f=new ArrayList<String>();
                f.add(s);
                map.put(key,f);
            }
            else {
                map.get(key).add(s);
            }
        }
        return new ArrayList<List<String>>(map.values());
    }
}
class Main {
    public static void main(String[] args){
        String[] n={"eat", "tea", "tan", "ate", "nat", "bat"};
        Solution S=new Solution();
        System.out.println(S.groupAnagrams(n));
    }
}
