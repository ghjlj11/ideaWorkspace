package lj.leetcode.code45;

class Main {
    public static void main(String[] args){
        Solution A=new Solution();
        int[] n={2,3,0,1,4};
        System.out.println(A.jump(n));
    }
}
class Solution {
    public int jump(int[] nums) {
        //return pp(0,nums,0,nums.length-1);
        return tan(nums);
    }
    private int pp(int pose,int[] nums,int times,int Mintimes){
        if(nums[pose]==0){
            return Mintimes;
        }
        if(nums[pose]+pose>=nums.length-1){
            return Math.min(Mintimes,times+1);
        }
        int k=0,minpose=pose;
        for(int i=pose+1;i<=pose+nums[pose];i++){
            if(k<nums[i]+i-pose){
                k=nums[i]+i-pose;
                minpose=i;
            }
        }
        Mintimes=Math.min(pp(minpose,nums,times+1,Mintimes),Mintimes);
        return Mintimes;
    }
    private int kk(int[] nums){
        if(nums.length==1){
            return 0;
        }
        int i=0,time=0;
        while(i+nums[i]<nums.length-1){
            int k=0,min=i;
            for(int j=i+1;j<=i+nums[i];j++){
                if(k<nums[j]+j-i){
                    k=nums[j]+j-i;
                    min=j;
                }
            }
            i=min;
            time++;
        }
        return time+1;
    }
    private int tan(int[] nums){
        int end=0,step=0,Maxpose=0;
        for(int i=0;i<nums.length-1;i++){
            Maxpose=Math.max(Maxpose,i+nums[i]);
            if(i==end){
                end=Maxpose;
                step++;
            }
        }
        return step;
    }
}
