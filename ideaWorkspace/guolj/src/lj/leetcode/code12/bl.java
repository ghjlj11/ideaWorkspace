package lj.leetcode.code12;

public class bl {
    public String intToRoman(int num) {
        StringBuffer s=new StringBuffer();
        int[] value={1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] py={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int i=-1;
        while(num>0){
            i++;
                if(num>=value[i]){
                    num-=value[i];
                    s.append(py[i]);
                    i=-1;
                }
        }
        return s.toString();
    }
}
