package lj.leetcode.code55;
class Solution {
    public boolean canJump(int[] nums) {
        return jump(nums,0,nums[0],false);
    }
    private boolean jump(int[] nums,int n,int num,boolean res){
        if(n+nums[n]>=nums.length-1){
            return true;
        }
        if(nums[n]==0){
            return false;
        }
        int max=0,pose=n;
        for(int i=n+1;i<=n+num;i++){
            if(max<i+nums[i]-n){
                max=i+nums[i]-n;
                pose=i;
            }
        }
        if(jump(nums,pose,nums[pose],res)){
            res=true;
        }
        return res;
    }
    public boolean jump2(int[] nums){
        int rm=0;
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(i<=rm){
                rm=Math.max(rm,i+nums[i]);
                if(rm>=n-1){
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }
}
class ad{
    public static void main(String[] args){
        int[] n={3,2,1,0,4};
        Solution A=new Solution();
        System.out.println(A.jump2(n));
    }
}
