package lj.leetcode.code20;

public class du {
    public boolean isValid(String s) {
        int n=s.length()/2;
        for(int i=0;i<n;i++){
            s=s.replace("()","").replace("[]","").replace("{}","");
            System.out.println(i);
        }
        //System.out.println(s);
        return s.length()==0;
    }
}
