package lj.leetcode.code207;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author 86187
 * 广度优先搜索：
 * res为记录下有哪些课程是需要先学习完第i门课程才可以继续学习的记录在第i个ArrayList中
 * 而need则是记录下第i门课程需要提前学习完几门课程才可以学习该门课程；
 * 如果need数组中没有一个0，则表示每一门课程都是有前提条件才可以学习的，则此时必定会形成环，则返回false
 * 通过队列qu记录下没有前提条件的课程，以及前提条件已经满足的课程
 * 队列qu每抛出一个temp值，则代表res里的第temp个ArrayList都可以清空，因为需要先学习第temp个才可以学习这些课程，
 * 而temp个已经是确定的可以学习的，如此继续排除，并记录每次qu抛出的次数，表示有几个课程已经是满足条件的。
 * 有numCourses个则表示全部可以学习。
 */
public class Solution2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] need = new int[numCourses];
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0 ; i < numCourses ; i ++){
            res.add(new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            res.get(prerequisite[1]).add(prerequisite[0]);
            ++ need[prerequisite[0]];
        }
        Queue<Integer> qu = new LinkedList<>();
        for(int i = 0 ; i < numCourses ; i ++){
            int k = need[i];
            if(k == 0){
                qu.offer(i);
            }
        }
        int visit = 0;
        int size = qu.size();
        while (size != 0){
            visit ++ ;
            int temp = qu.poll();
            List<Integer> re = res.get(temp);
            for (Integer k : re) {
                --need[k];
                if(need[k] == 0){
                    qu.offer(k);
                }
            }
            size = qu.size();
        }
        return visit == numCourses;
    }
}
