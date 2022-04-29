package big_integer;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author 86187
 */
public class Big {
    public static void main(String[] args){
        BigInteger bigInteger = new BigInteger("700000000000000000000");
        BigInteger bigInteger1 = new BigInteger("300000000000000000000000000");
        BigInteger b1 = bigInteger1.pow(4);
        System.out.println(b1);
        Objects.hashCode(bigInteger);
    }
}
