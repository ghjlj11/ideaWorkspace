package lj.leetcode.code152;

/**
 * @author 86187
 */
public class Main {
    private static final StringBuffer STRING_BUFFER = new StringBuffer("123456");
    public static void main(String[] args){
        int[] nums = {-2,0,-1};
        Main m = new Main();
        Main.STRING_BUFFER.reverse();
        System.out.println(Main.STRING_BUFFER);
        Solution A = new Solution();
        System.out.println(A.maxProduct(nums));
    }
}
