package my_inputstream;

import java.io.*;

/**
 * @author 86187
 */
public class IOs {
    public static void main(String[] args) throws IOException {
        String path = "D:\\ideaWorkspace\\test\\test02.txt";
        File file = new File(path);
        InputStream in = new FileInputStream(file);
        File file1 = new File("D:\\ideaWorkspace\\test\\test.txt");
        OutputStream ou = new FileOutputStream(file1);
        in.transferTo(ou);
        in.close();
        ou.flush();
        ou.close();
    }
}
