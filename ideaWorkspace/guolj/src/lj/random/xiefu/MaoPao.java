package lj.random.xiefu;

import java.util.Arrays;

/**
 * @author 86187
 */
public class MaoPao {

    public static void main(String[] args) {
        int[] arr = {11, 6, 5, 3, 4, 55, 9};
        MaoPao.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr){
        int t = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[i] < arr[j]){
                    t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }
}
