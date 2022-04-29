package bufferedInputString;

import java.io.*;
import java.util.Arrays;

/**
 * @author 86187
 */
public class Test01 {
    public static  void main(String[] args) throws IOException {
        File file = new File("D:\\ideaWorkspace\\test\\test.txt");
        InputStream in = new FileInputStream(file);
        BufferedInputStream buf = new BufferedInputStream(in);
        byte[] bytes = new byte[100];
        int read = buf.read();
        buf.read(bytes);
        System.out.println(Arrays.toString(bytes));
        buf.close();;
        in.close();
    }
}
