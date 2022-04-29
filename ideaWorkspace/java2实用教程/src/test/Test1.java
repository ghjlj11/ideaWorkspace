package test;

import java.util.*;

/**
 * @author 86187
 */
public class Test1 {
    public static void main(String[] args) {
        int[ ] data = { 19 , 22 , 98 , 100 , 56 , 77 , 45 , 72 , 0 , -1 };
        for(int i = 0; i < data.length - 1 ; i ++){
            for(int j = 0 ; j < data.length - i - 1; j ++){
                if(data[j] > data[j + 1]){
                    data[j] = data[j] ^ data[j + 1];
                    data[j + 1] = data[j] ^ data[j + 1];
                    data[j] = data[j] ^ data[j + 1];
                }
            }
        }
        System.out.println(Arrays.toString(data));

    }
}
