package my_regex;

import java.util.Random;
import java.util.regex.Pattern;

/**
 *   ? 0个或者一个
 *   * 0个或者多个
 *   + 一个或者多个
 *   . 匹配任意一个字符， 除去换行
 *   [a-zA-z0-9] 匹配一个 大小写字母，数字
 *   {2,5} 匹配2-5个， 包括2，5个
 *   | 或者
 *   [] 表示由[]里的字符组成的， [abc] 可以匹配 a, b, c,但不能ab,ac,abc
 *   [^0-9] 匹配非数字
 *   \d 数字   ;   \D 非/d
 *   \w 英文，数字，下划线  ; \W 同
 *   \s 空白符 包括tab， 换行 ； \S 同
 *   \b 标注字符的边界
 *   .+  贪婪匹配  匹配任意字符串
 *   .+?  懒惰匹配  即匹配到最小符合的字符串就停止， 继续从下一个字符开始匹配，即当字符串abcdefg， 可以匹配a,b,c,d,e,f,g  ，但是不会匹配ab， bc
 * @author 86187
 */
public class Test01 {
    public static void main(String[] args) throws Exception {
        String regex = "(((1?\\d\\d?)|(2([0-4]\\d|5[0-5])))\\.){3}(2([0-4]\\d|5[0-5])|(1?\\d\\d?))";
        Random random = new Random();
        long star = System.currentTimeMillis();
        for (int i = 0; i < 100086; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < 3; j++) {
                s.append(random.nextInt(0,256)).append(".");
            }
            s.append(random.nextInt(0,256));
            System.out.println("字符："+ s + "匹配结果：" + Pattern.matches(regex, s));
            if(!Pattern.matches(regex, s)){
                throw new Exception();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - star) / 1000 + "s");
    }
}
