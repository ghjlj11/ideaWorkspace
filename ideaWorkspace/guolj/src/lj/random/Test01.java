package lj.random;

/**
 * @author 86187
 */
public class Test01 {
    public static void main(String[] args){
        Test01 test01 = new Test01();
        int[] arr = {1,2,3,4,5,6,7,8,9, 10};
        for (int i = 1; i < 10; i++) {
            System.out.println(test01.findByTow(arr, i));
        }
        int byTow = test01.findByTow(arr, 4);
        System.out.println(byTow);
    }

    public int findByTow(int[] arr, int tar){
        int end = arr.length;
        int start = 0;
        int mid = ( end  - start ) / 2 + start;
        while (start < end){
            int temp = arr[mid];
            if(temp == tar){
                return mid;
            }
            else if(temp < tar){
                start = mid;
            }
            else {
                end = mid;
            }
            mid = ( end  - start ) / 2 + start;
        }
        return mid;
    }
}
