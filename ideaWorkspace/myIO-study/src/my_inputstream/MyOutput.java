package my_inputstream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 86187
 */
public class MyOutput {
    public static void main(String[] args) throws IOException {

        File file = new File("D:\\ideaWorkspace\\test\\test.txt");
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
