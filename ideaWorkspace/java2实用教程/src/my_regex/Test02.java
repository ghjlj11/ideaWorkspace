package my_regex;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author 86187
 * 匹配小数， 不包含科学计数法
 */
public class Test02 {
    public static void main(String[] args) throws Exception {
        String pattern = "^\\d{1,30}(\\.\\d{1,30})?$";
        Random random = new Random();
        System.out.println(Pattern.matches(pattern, null));
//        for (int i = 0; i < 10086; i++) {
//            float v = random.nextFloat();
//            String s = v + "";
//            boolean matches = Pattern.matches(pattern, s);
//            if(!matches){
//                throw new Exception("匹配失败:" + s + "， 共匹配" + (i + 1) + "个结果");
//            }
//            System.out.println("匹配成功:" + s);
//        }

        String pat = "\\d{1,34}";
        System.out.println(Pattern.matches(pat, "a"));
    }
}
