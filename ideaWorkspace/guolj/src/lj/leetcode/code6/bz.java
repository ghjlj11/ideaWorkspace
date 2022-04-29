package lj.leetcode.code6;

public class bz {
    public String convert(String s, int numRows) {
        char[] st=new char[s.length()];
        int k=0;
        if(numRows==1||numRows>=s.length()){
            return s;
        }
        for(int i=0;i<numRows;i++){
            for(int j=0;j<s.length();j++){
            if(j%(2*numRows-2)==i||j%(2*numRows-2)==2*numRows-2-i){
                st[k]=s.charAt(j);
                k++;
            }}
        }
        return String.valueOf(st);
    }
}
