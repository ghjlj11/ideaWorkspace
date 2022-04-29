package lj.leetcode.code825;
import java.util.Arrays;
class Solution {
    public int numFriendRequests(int[] ages) {
        int n=ages.length;
        Arrays.sort(ages);
        int i=0,j=0,sum=0;
        for(int age:ages){
            if(age<15){
                continue;}
            while(ages[i]<=( 0.5 * age + 7 )){++i;}
            while(j+1<n&&ages[j+1]<=age){++j;}
            sum+=j-i;
        }
        return sum;
        /*int n=ages.length;
        Arrays.sort(ages);
        int i=0,j=0,sum =0;
        for (int age:ages) {
            if(age<15){
                continue;}
            while(ages[i]<=0.5*age+7){++i;}
            while(j+1<n&&ages[j+1]<=age){++j;}
            sum+=j-i;
        }
        return sum;*/
    }
}

