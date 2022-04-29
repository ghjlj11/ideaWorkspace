package lj.leetcode.code399;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86187
 */
public class Solution {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
       int len = equations.size();
       int len2  = queries.size();
       double[] res = new double[len2];
       UnionChar unionChar = new UnionChar(2 * len);
       Map<String, Integer> map = new HashMap<>(2 * len);

       int index = 0;
        for (int i = 0 ; i < len ; i ++) {
            List<String> equation = equations.get(i);
            String a = equation.get(0);
            String b = equation.get(1);
            if(!map.containsKey(a)){
                map.put(a, index ++);
            }
            if(!map.containsKey(b)){
                map.put(b, index ++);
            }
            unionChar.union(map.get(a), map.get(b), values[i]);
        }

        for(int i = 0 ; i < len2 ; i ++){
            List<String> query = queries.get(i);
            Integer index1 = map.get(query.get(0));
            Integer index2 = map.get(query.get(1));
            if(index1 == null || index2 == null){
                res[i] = -1;
            }
            else {
                res[i] = unionChar.result(index1, index2);
            }
        }
        return res;
    }

    class UnionChar{
        private int[] parent;
        private double[] weight;

        public UnionChar(int n) {
            this.parent = new int[n];
            this.weight = new double[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i ;
                weight[i] = 1;
            }
        }

        public  int find(int x){
            if(parent[x] == x){
                return x;
            }
            int par = parent[x];
            parent[x] = find(parent[x]);
            weight[x] *= weight[par];
            return parent[x];
        }

        public void union(int x, int y, double value){
            int parX = find(x);
            int parY = find(y);

            if(parX == parY){
                return ;
            }
            parent[parX] = parY;

            weight[parX] = weight[y] * value / weight[x] ;
        }

        public double result(int x, int y){

            int parX = find(x);

            int parY = find(y);
            if(parX == parY){
                return weight[x] / weight[y] ;
            }
            else {
                return -1;
            }
        }
    }
}
