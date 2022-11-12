package work;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * 测试 代码块中声明的变量
 */
public class TestVar {
    int a = 6;
    {
        int a = 3;
        this.a = 44;
        System.out.println("inner:" + a);
    }
    public TestVar(){
        int a = 12;
        System.out.println("constructor:" + a);
    }

    public static void main(String[] args) {
        TestVar testVar = new TestVar();
        System.out.println(testVar.a);
        Calendar instance = Calendar.getInstance();
        int i = instance.get(Calendar.DAY_OF_WEEK);
        System.out.println(i);
        System.out.println(instance.get(Calendar.MONTH));
        System.out.println(instance.get(Calendar.DAY_OF_WEEK));
    }
}
