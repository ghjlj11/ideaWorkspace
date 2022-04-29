package lj.leetcode.code31;

public class Main {
    public static void main(String[] args){
        int[] a={5,1,1};
        Solution2 d=new Solution2();
        d.nextPermutation(a);
        for(int i=0;i<3;i++){
            System.out.println(a[i]);
        }
    }
}
