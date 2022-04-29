package lj.leetcode.code74;

public class Main {
    public static void main(String[] args){}
}
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n=matrix.length;
        int m=matrix[0].length;
        int l=0,r=m-1,mid=(l+r)/2;
        while (l<=r){
            if(target<matrix[0][mid]){
                r=mid-1;
                mid=(l+r)/2;
            }
            else if(target>matrix[0][mid]){
                l=mid+1;
                mid=(l+r)/2;
            }
            else {
                return true;
            }
        }
        l=0;
        r=n-1;
        mid=(l+r)/2;
        while (l<r-1){
            if(target>matrix[mid][m-1]){
                l=mid;
                mid=(l+r)/2;
            }
            else if(target<matrix[mid][m-1]){
                r=mid;
                mid=(l+r)/2;
            }
            else{
                return true;
            }
        }
        int row = r;
        r = m-1;
        l = 0;
        mid = (l+r)/2;
        while (l<=r){
            if(target<matrix[row][mid]){
                r=mid-1;
                mid=(l+r)/2;
            }
            else if(target>matrix[row][mid]){
                l=mid+1;
                mid=(l+r)/2;
            }
            else {
                return true;
            }
        }
        return false;
    }
}
