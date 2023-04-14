package lj.random;


import java.util.Arrays;

/**
 * @author 86187
 */
public class InsertSort {
    public static void main(String[] args) {
        int[][] arrs = {{1,5,46,6}, {2,51,15,32,125}, {3,41,68,24,6},{4,82,1,88,62},{5,87,44}};
        int[] res = new int[arrs.length];
        for (int i = 0; i < arrs.length; i++) {
            res[i] = getSecondMax(arrs[i]);
        }
        System.out.println(Arrays.toString(res));
    }

    /**
     *
     * @param arr
     * @return
     */
    public static int getSecondMax(int[] arr){
        for (int i = 0 ; i < 2 ; i ++){

            for (int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j] > arr[j + 1]){
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
        return arr[arr.length - 2];
    }
}
