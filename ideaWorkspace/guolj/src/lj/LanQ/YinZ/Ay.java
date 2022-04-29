package lj.LanQ.YinZ;
import java.text.DecimalFormat;
import java.util.*;
public class Ay {
    public double sd(int n,int m){
        if(m<n){
            return 0;
        }
        Set<Integer> set=new HashSet<Integer>();
        Set<Integer> sums=new HashSet<Integer>();
        for(int i=1;i<=n;i++){
            sums.add(i);
        }
        return (double) yin(0,n,m,0,set,sums)/(Math.pow(n,m));
    }
    private int yin(int times,int n,int m,int sum,Set<Integer> set,Set<Integer> sums){
        if(sum==m){
            if(set.containsAll(sums)){
                times++;
            }
            return times;
        }
        for(int i=1;i<=n;i++){
            if(!set.contains(i)){
                set.add(i);
                times=yin(times,n,m,sum+1,set,sums);
                set.remove(i);
            }
            else {
                times=yin(times,n,m,sum+1,set,sums);
            }
        }
        return times;
    }
}
class dd{
    public static void main(String args[]){
        int n=1,m=1;
        Ay s=new Ay();
        DecimalFormat df=new DecimalFormat(".0000");
        System.out.println(df.format(s.sd(n,m)));
    }
}
