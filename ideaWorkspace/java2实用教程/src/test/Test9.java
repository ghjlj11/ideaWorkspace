package test;

import java.time.LocalDate;

/**
 * @author 86187
 */
public class Test9 {
    public static void main(String[] args) {
        LocalDate l = LocalDate.of(1234,12,23);
        System.out.println(l.getDayOfYear());
    }
}
