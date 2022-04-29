package lj.leetcode.code30;

public class Main {
    public static void main(String[] args){
        String s="wordgoodgoodgoodbestword";
        String[] word={"word","good","best","good"};
        Solution B=new Solution();
        System.out.println(B.findSubstring(s,word));
    }
}
