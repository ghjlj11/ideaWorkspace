package work;

import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2023/10/13 14:46
 */
public class TestDate {
    public static void main(String[] args) {
        String value1 = "2023-10-13 14:16:22";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(value1, dateTimeFormatter);
        System.out.println(parse.getMonth().getValue());
        String s1 = "123";
        String s2 = new String("123");

        System.out.println(System.identityHashCode(s1));
        System.out.println(System.identityHashCode(s2));

        String s3 = "333";
        String s4 = "444";
        String s = "333444";
        String s5 = "333" + "444";
        String s6 = s3 + s4;
        System.out.println(s == s5);
        System.out.println(s == s6);
        // System.out.println(s == s7);
        String tt = new StringBuilder("ja").append("va").toString();
        System.out.println("k,k1==========>");
        System.out.println(tt.intern() == tt);
        System.out.println(s == s6.intern());
        int i = 0;
        Set<String> stringSet = new HashSet<>();
//        while (true) {
//            stringSet.add(String.valueOf(i ++).intern());
//            System.out.println(i);
//        }
        StringBuilder stringBuilder = new StringBuilder("22");
        System.out.println(stringBuilder.append((String) null));
        Integer i1 = 2, i2 = null;
        assert i2 != null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("12", "22");
        Map map = (Map) jsonObject;
        System.out.println(map);
        //System.out.println(i1 > i2);
        String inter = "kkjjhhgg";
        String inter2 = "kkjjhhgg";
        System.out.println(inter == inter2);
        String ss1 = "btteTdZY2dvakFrRCtOTXgxRm9SRWNBMmQ4QUVvRlFwSWc4TTQrZTNLYnRVRkxhdUU4MnVuRG1kTEJoc1djb3VoYl9feXdiaXAtc2l0Mi53YWxzaW4uY29t__d6fabea4c13b8e202b2d856383ccab83_1700184132937dccore0iuap-apcom-workbench5f1d4d86YT";
        String ss2 = "btteTdZY2dvakFrRCtOTXgxRm9SRWNBMmQ4QUVvRlFwSWc4TTQrZTNLYnRVRkxhdUU4MnVuRG1kTEJoc1djb3VoYl9feXdiaXAtc2l0Mi53YWxzaW4uY29t__d6fabea4c13b8e202b2d856383ccab83_1700184132937dccore0iuap-apcom-workbench5f1d4d86YT";
        System.out.println(ss1.equals(ss2));
        String sss1 = null;
        String sss2 = "123";
        String sss3 = sss1 + sss2;
        System.out.println(sss1 + sss2);
        String ki = "[Y,N]";
        System.out.println(Pattern.matches(ki, "YN"));
        List<String> list = new ArrayList<>();
    }
}
