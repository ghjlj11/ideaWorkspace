package my_inputstream;

import java.io.*;
import java.nio.file.Path;

/**
 * @author 86187
 */
public class MyOutput {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\ideaWorkspace\\test02.txt");
        String path = file.getPath();
        OutputStream outputStream = new FileOutputStream(path);

        byte[] b = new byte[]{97,98,99,100};
        String s = "你是谁";
        char[] chars = s.toCharArray();
        outputStream.write(b);
        outputStream.flush();
        outputStream.close();
    }
}
