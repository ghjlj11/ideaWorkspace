package lj.leetcode.code54;
import java.util.*;
public class Main {
    public static void main(String[] args){
        int[][] n={{1,2,3},{4,5,6},{7,8,9}};
        Solution A=new Solution();
        System.out.println(A.spiralOrder(n));
    }
}
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res=new ArrayList<Integer>();
        int sum=0,num=matrix.length,num1=matrix[0].length,x=0,y=0,sums=num*num1;
        if(num==1||num1==1){
            for(int i=0;i<num;i++){
                for(int j=0;j<num1;j++){
                    res.add(matrix[i][j]);
                }
            }
            return res;
        }
        while(sum<sums){
            if(sum+1==sums){
                res.add(matrix[x][y]);
                break;
            }
            for(int i=x;i<num1-x-1;i++){
                res.add(matrix[x][i]);
                sum++;
                if(sum==sums){break;}
            }
            for(int j=y;j<num-y-1;j++){
                res.add(matrix[j][num1-y-1]);
                sum++;
                if(sum==sums){break;}
            }
            for(int i=num1-x-1;i>x;i--){
                res.add(matrix[num-x-1][i]);
                sum++;
                if(sum==sums){break;}
            }
            for(int j=num-y-1;j>y;j--){
                res.add(matrix[j][y]);
                sum++;
                if(sum==sums){break;}
            }
            x++;
            y++;
        }
        return res;
    }
}
