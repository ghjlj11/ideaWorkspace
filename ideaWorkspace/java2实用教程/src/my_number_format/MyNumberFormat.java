package my_number_format;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author 86187
 */
public class MyNumberFormat {
    public static void main(String[] args){
        double sum = 0, item = 0;
        boolean ca = true;
        NumberFormat format = new DecimalFormat("#.00");
        format.setRoundingMode(RoundingMode.DOWN);
        double d1 = 1.234;
        double d2 = 4.35;
        System.out.println(format.format(d1 * d2));
        for(String s : args){
            try{
                item = Double.parseDouble(s);
                sum += item;
            }
            catch(NumberFormatException e){
                System.out.println("你是傻逼吧!" + e);
            }
            finally{
                System.out.println("郭欢军");
            }
        }
        if(ca){
            System.out.println("sum = "+ sum);
        }
    }
}
