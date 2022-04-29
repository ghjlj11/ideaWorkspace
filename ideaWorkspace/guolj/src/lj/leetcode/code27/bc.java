package lj.leetcode.code27;

public class bc {
    public int removeElement(int[] nums, int val) {
        int n=nums.length,m=0;
        for(int i=0;i<n;i++){
            if(nums[i]!=val){
                nums[m]=nums[i];
                m++;
            }
        }
        return m;
    }
}
