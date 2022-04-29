package my_inputstream;

import java.io.*;
import java.util.Arrays;

/**
 * @author 86187
 */
/**
 * File parentFile = file.getParentFile();
 *  String[] list = parentFile.list();
 *  System.out.println(Arrays.toString(list));
 *  System.out.println(file.toString());
 * @author 86187
 */
public class MyInput {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/ideaWorkspace/test.txt");
        String path = file.getPath();
        InputStream inputStream = new FileInputStream(path);
        int len = inputStream.available();
        byte[] b = new byte[len];
        inputStream.read(b);
        String s = new String(b);
        System.out.println(Arrays.toString(b));
        System.out.println(s);
        inputStream.close();
    }
}
