package test;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author 86187
 */
public class Test5 {
    public static void main(String[] args) throws IOException {
        File ori = new File("file.txt");
        File des = new File("file-gbk.txt");
        InputStream is = new FileInputStream(ori);
        InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
        OutputStream os = new FileOutputStream(des);
        OutputStreamWriter osw = new OutputStreamWriter(os, Charset.forName("GBK"));
        char[] chars = new char[(int)ori.length()];
        isr.read(chars);
        osw.write(chars);
        osw.flush();
        os.flush();
        osw.close();
        os.close();
        isr.close();
        is.close();
    }
}
