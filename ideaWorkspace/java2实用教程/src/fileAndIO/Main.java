package fileAndIO;

import java.io.*;
import java.util.Random;

/**
 * @author 86187
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int[] a = new int[5];
        double random = Math.random();
        System.out.println(random);
        Random random1 = new Random();
        System.out.println(random1.nextInt(10));
    }
}
