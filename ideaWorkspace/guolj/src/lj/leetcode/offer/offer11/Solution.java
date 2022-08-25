package lj.leetcode.offer.offer11;

class Solution {
    public int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1 ;
        while (l < r){
            int mid = (r - l) / 2 + l;
            if(numbers[mid] < numbers[r]){
                r = mid;
            }
            else if(numbers[mid] > numbers[r]){
                l = mid + 1;
            }
            else {
                r --;
            }
        }
        return numbers[l];
    }
}
