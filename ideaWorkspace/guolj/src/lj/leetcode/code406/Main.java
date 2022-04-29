package lj.leetcode.code406;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args) {
        int[][] k = new int[][]{{6,0},{5,0},{4,0},{3,2},{2,2},{1,4}};
        Solution s =new Solution();
        k = s.reconstructQueue(k);
        for(int i = 0 ; i < k.length ; i ++){
            System.out.println(Arrays.toString(k[i]));
        }
    }
}
