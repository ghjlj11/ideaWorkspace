package lj.leetcode.code31;

public class Solution {
    public void nextPermutation(int[] nums) {
        int n=nums.length,i,u,o,m;
        for(i=n-1;i>=0;i--){
            int k=i;
            int t=101;
            for(int j=n-1;j>=i;j--){
                if(nums[j]>nums[i]&&t>nums[j]){
                    t=nums[j];
                    k=j;
                }
            }
            if(k!=i){
                m=nums[i];
                nums[i]=t;
                nums[k]=m;
                for(u=i+1;u<n-1;u++){
                    for(o=u+1;o<n;o++){
                        if(nums[o]<nums[u]){
                            m=nums[o];
                            nums[o]=nums[u];
                            nums[u]=m;
                        }
                    }
                }
                break;
            }
        }
        if(i==-1){
            u=0;o=n-1;
            while (u<(n/2)){
                m=nums[u];
                nums[u]=nums[o];
                nums[o]=m;
                u++;
                o--;
            }
        }
    }
}
