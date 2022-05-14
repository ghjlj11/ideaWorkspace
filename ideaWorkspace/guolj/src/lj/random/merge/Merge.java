package lj.random.merge;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 * @author 86187
 */
public class Merge {
    public void sort(int[] nums, int starIndex, int endIndex){
        if(starIndex >= endIndex){
            return;
        }
        int mid = (starIndex + endIndex)/2;
        sort(nums, starIndex, mid);
        sort(nums, mid + 1,endIndex);
        merge(nums, starIndex, mid, endIndex);
    }
    private void merge(int[] nums, int starIndex, int mid, int endIndex){
        int[] temp = new int[endIndex - starIndex + 1];
        int p1 = starIndex, p2 = mid + 1, k = 0;
        while (p1 <= mid && p2 <= endIndex){
            if(nums[p1] < nums[p2]){
                temp[k++] = nums[p1++];
            }
            else {
                temp[k++] = nums[p2++];
            }
        }
        while (p1 <= mid){
            temp[k++] = nums[p1++];
        }
        while (p2 <= endIndex){
            temp[k++] = nums[p2++];
        }
        for(int i = 0; i < k ; i++){
            nums[starIndex++] = temp[i];
        }
    }
}
class Text {
    public static void main(String[] args){
        int[] nums = {5,6,7,2,1,3};
        Merge S = new Merge();
        S.sort(nums, 0, nums.length - 1);
        for(int i = 0; i < nums.length; i++){
            System.out.println(nums[i]);
        }
    }
}
