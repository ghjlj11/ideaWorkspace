package lj.random.quicksort;

/**
 * @author ghj
 */
public class QuickSort {
    public static void main(String[] args){
        int[] n = {5,6,3,3,6,1,0};
        Quick quick = new Quick();
        quick.sort(n,0,n.length-1);
        for(int i = 0 ; i < n.length; i++){
            System.out.println(n[i]);
        }
    }
}
class Quick{
    public void sort(int[] n ,int  starIndex , int endIndex){
        int star = starIndex , end = endIndex;
        while (star < end){
            while (star < end){
                if(n[end] < n[star]){
                    int temp = n[end] ;
                    n[end] = n[star] ;
                    n[star] = temp;
                    break;
                }
                end--;
            }
            while (star < end){
                if(n[star] > n[end]){
                    int temp = n[end];
                    n[end] = n[star];
                    n[star] = temp;
                    break;
                }
                star++;
            }
        }
        if(star - 1 > starIndex ){
            sort(n , starIndex , star - 1);
        }
        if(end + 1 < endIndex){
            sort(n , end + 1, endIndex);
        }
    }
}
