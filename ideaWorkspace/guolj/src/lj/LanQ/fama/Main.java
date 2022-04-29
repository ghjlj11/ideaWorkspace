package lj.LanQ.fama;

import java.util.*;
/**
 * @author 86187
 */
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for(int i = 0; i < N; i ++){
            nums[i] = sc.nextInt();
        }
        if(N == 1){
            System.out.println(nums[0]);
        }
        List<Integer> ans1 = new ArrayList<Integer>();
        List<Integer> ans2 = new ArrayList<Integer>();
        ans1.add(0);
        ans1.add(nums[0]);
        ans2.add(nums[0]);
        for(int i = 1; i < N; i ++){
            if(!ans2.contains(nums[i])){
                ans2.add(nums[i]);
            }
            for(int j = 0; j < ans1.size(); j ++){
                int num1 = ans1.get(j);
                int num2 = Math.abs(nums[i] - num1);
                int num3 = num1 + nums[i];
                if(!ans2.contains(num2)){
                    ans2.add(num2);
                }
                if(!ans2.contains(num3)){
                    ans2.add(num3);
                }
            }
            ans1.clear();
            ans1.addAll(ans2);
        }
        System.out.println(ans2.size() - 1);
    }
}
