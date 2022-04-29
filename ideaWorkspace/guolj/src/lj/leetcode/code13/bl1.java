package lj.leetcode.code13;

public class bl1 {
    public int romanToInt(String s) {
        int[] value={1000,500,100,50,10,5,1};
        char[] py={'M','D','C','L','X','V','I'};
        int[] value2={900,400,90,40,9,4,};
        String[] py2={"CM","CD","XC","XL","IX","IV"};
        int res=0,i=0,j,k=0;
        while(i<s.length()){
            j=0;
            while(i<s.length()-1&&j<py2.length){
                if(s.substring(i,i+2).equals(py2[j])){
                res+=value2[j];
                i+=2;
                System.out.println(value2[j]);
                }
                j++;
            }
            if(i<s.length()){
            for(k=0;k<py.length;k++){
                if(s.charAt(i)==py[k]){
                    res+=value[k];
                    i++;
                    System.out.println(value[k]);
                    break;
                }
            }}
        }
        return res;
    }
}
