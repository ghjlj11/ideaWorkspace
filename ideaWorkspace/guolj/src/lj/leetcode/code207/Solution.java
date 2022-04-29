package lj.leetcode.code207;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 86187
 *
 * 深度优先搜索：
 * 尽力寻找环的存在，并标记下找过的路线。
 * 记忆化,0为还未访问，1为正在访问，2为访问完成。经过回溯后只存在两种情况
 * 要么为0，要么为2，如果为1则说明还在搜索,说明此时已经成环，此时noCircle为false并返回。
 */

public class Solution {
    List<List<Integer>> res ;
    int[] vi;
    boolean noCircle = true;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        res = new ArrayList<>();
        vi = new int[numCourses];
        for(int i = 0; i < numCourses ; i ++){
            res.add(new ArrayList<>());
        }
        /**
         * 此时找出由prerequisite[1]可以推出的所有路线，即先完成prerequisite[1]后才有资格完成该课程
         */
        for (int[] prerequisite : prerequisites) {
            res.get(prerequisite[1]).add(prerequisite[0]);
        }
        for(int i = 0; i < numCourses && noCircle ; i ++){
            if(vi[i] == 0){
                dfs(i);
            }
        }
        return noCircle;
    }
    private void dfs(int i){
        vi[i] = 1;
        List<Integer> temp = res.get(i);
        int len = temp.size();
        for(int k = 0 ; k < len && noCircle ; k ++){
            int z = temp.get(k);
            if(vi[z] == 0){
                dfs(z);
            }
            else if(vi[z] == 1){
                noCircle = false;
                return;
            }
        }
        vi[i] = 2;
    }
}
