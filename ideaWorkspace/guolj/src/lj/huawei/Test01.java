package lj.huawei;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author guohuanjun1
 * @date 2023/8/6 22:16
 */
public class Test01 {
    public static void main(String[] args) {
        final String Y = "Y";
        String pattern = "[1-9]\\d{0,2}/(N|Y)";
        List<Integer> clazz1 = new ArrayList<>();
        List<Integer> clazz2 = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        List<String> tags = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String nextLine = scanner.nextLine();
        String[] strings = nextLine.split(" ");
        if(strings.length == 0 ){
            System.out.println("ERROR");
            return;
        }
        for (String string : strings) {
            if(Pattern.matches(pattern, string)){
                String[] split = string.split("/");
                numbers.add(Integer.valueOf(split[0]));
                tags.add(split[1]);
            }
            else {
                System.out.println("ERROR" + string);
                return;
            }
        }
        clazz1.add(numbers.get(0));
        boolean isOne = true;
        for (int i = 1; i < numbers.size(); i++) {
            if(isOne){
                if(tags.get(i).equals(Y)){
                    clazz1.add(numbers.get(i));
                }
                else {
                    clazz2.add(numbers.get(i));
                    isOne = false;
                }
            }
            else {
                if(tags.get(i).equals(Y)){
                    clazz2.add(numbers.get(i));
                }
                else {
                    clazz1.add(numbers.get(i));
                    isOne = true;
                }
            }
        }
        clazz1.sort((Comparator.comparingInt(o -> o)));
        clazz2.sort((Comparator.comparingInt(o -> o)));
        if(clazz2.size() == 0){
            printList(clazz1);
        }
        else {
            boolean oneMin = clazz1.get(0) < clazz2.get(0);
            if(oneMin){
                printList(clazz1);
                System.out.println();
                printList(clazz2);
            }
            else {
                printList(clazz2);
                System.out.println();
                printList(clazz1);
            }
        }
    }
    public static void printList(List<Integer> list){
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }
}
