package lj.leetcode.code20;

public class bu {
    public boolean isValid(String s) {
        StringBuffer k=new StringBuffer(s);
        int n=k.length();
        if(s==null||n<2||n%2!=0){return false;}
        for(int i=0;i<n/2;i++){
            for(int j=0;j<k.length()-1;j++){
                int m=j+1;
                if((k.charAt(j)=='('&&k.charAt(m)==')')||(k.charAt(j)=='['&&k.charAt(m)==']')||(k.charAt(j)=='{'&&k.charAt(m)=='}')){
                    k.delete(j,m+1);
                    j=-1;
                }
            }
            if(k.length()==0){
                return true;
            }
        }
        if(k.length()!=0){
            return false;
        }
        return true;
    }
}
