package lj.random.exercise;

/**
 * @author 86187
 */
public class BaseType {
    public static void main(String[] args){
        boolean BooleanType = false;
        boolean booleanType = true;
        char CharType = 's';
        byte ByteType = 0;
        ByteType = (byte)(CharType + ByteType);
        short ShortType = 34;
        ShortType = (short)(ShortType + (short)22);
        int IntType = 6;
        long LongType = 34L;
        IntType = IntType + (int)LongType;
        ShortType = (short)(ShortType + LongType);
        float FloatType = 2.34F;
        double DoubleType = 3.3;
        System.out.println(ByteType);
        char c = 5 + 'a';
        System.out.println(c);
    }
}
