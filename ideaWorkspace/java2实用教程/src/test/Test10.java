package test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 86187
 */
public class Test10 {
    public static void main(String[] args){
        int array[][] = {
                { 12, 34, 68, 32, 9, 12, 545 },
                { 34, 72, 82, 57, 56, 0, 213 },
                { 12, 34, 68, 32, 21, 945, 23 },
                { 91, 10, 3, 2354, 73, 34, 18 },
                { 12, 83, 189, 26, 27, 98, 33 },
                { 47, 23, 889, 24, 899, 23, 657 },
                { 12, 34, 68, 343, 878, 235, 768 },
                { 12, 34, 98, 56, 78, 12, 546 },
                { 26, 78, 2365, 78, 34, 256, 873 }
        };
        Test10 t = new Test10();
        t.sortIntArrays(array, 6, false);
        for(int i = 0 ; i < array.length ; i ++){
            System.out.println(Arrays.toString(array[i]));
        }
    }
    private void sortIntArrays( int[][] a , int sortCols , boolean sortOrd ){
        Comparator<int[]> comparator1 = (o1, o2) -> o1[sortCols] - o2[sortCols];
        Comparator<int[]> comparator2 = (o1, o2) -> o2[sortCols] - o1[sortCols];
        if(sortOrd){
            Arrays.sort(a,comparator1);
        }
        else {
            Arrays.sort(a, comparator2);
        }
    }
}
