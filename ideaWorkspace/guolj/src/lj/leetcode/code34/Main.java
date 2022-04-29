package lj.leetcode.code34;

public class Main {
    public static void main(String[] args){
        int[] num={0,0,1,1,2,2,2,2,3,3,4,4,4,5,6,6,6,7,8,8};
        int t=4;
        Solution b=new Solution();
        int[] n=b.searchRange(num,t);
        System.out.println(n[0]+" "+n[1]);
    }
}
