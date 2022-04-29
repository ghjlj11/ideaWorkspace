package lj.random.seven;

public class Example7_4 {
    public static void main(String[] args){
        try {
            Integer a=Integer.parseInt("34566");
            Integer b=Integer.parseInt("s234f");
        }
        catch (Exception e){
            System.out.println("发生异常："+e.getMessage());
        }
    }
}
