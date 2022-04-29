package lj.LanQ.YangHui;
import java.util.*;
public class YangHui {
    public static void sd(int m){
        if(m==1){
            System.out.println(1);
            return;
        }
        List<Integer> ans1=new ArrayList<Integer>();
        List<Integer> ans2=new ArrayList<Integer>();
        ans1.add(1);
        ans1.add(1);
        long num=3;
        for(int i=2;;i++){
            for(int j=0;j<=i;j++){
                num++;
                if(j==0||j==i){
                    ans2.add(1);
                }
                else{
                    ans2.add(ans1.get(j-1)+ans1.get(j));
                    if(ans2.get(ans2.size()-1)==m){
                        System.out.println(num);
                        return;
                    }
                }
            }
            ans1.clear();
            ans1.addAll(ans2);
            ans2.clear();
        }
    }
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        sd(in.nextInt());
    }
}
