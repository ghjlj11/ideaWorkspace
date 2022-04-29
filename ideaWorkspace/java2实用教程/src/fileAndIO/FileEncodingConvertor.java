package fileAndIO;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author 86187
 */
public class FileEncodingConvertor {
    public FileEncodingConvertor() {
    }
    public static FileEncodingConvertor getInstance(){
        return new FileEncodingConvertor();
    }
    public void convert(File ori, Charset oriC, File des, Charset desC) throws IOException {
        InputStream is = new FileInputStream(ori);
        InputStreamReader isr = new InputStreamReader(is, oriC);
        OutputStream os = new FileOutputStream(des);
        OutputStreamWriter osw = new OutputStreamWriter(os, desC);
        char[] chars = new char[(int)ori.length()];

        isr.read(chars);
        osw.write(chars);
        osw.close();
        os.close();
        isr.close();
        is.close();
    }
    public void batch(File ori, Charset oriC, File des, Charset desC) throws IOException {
        File[] files1 = ori.listFiles();
        File[] files2 = des.listFiles();
        for(int i = 0 ; i < files1.length ; i ++){
            File file1 = files1[i];
            File file2 = files2[i];
            convert(file1, oriC, file2, desC);
        }
    }
    public void toUTF8(File ori, File des) throws IOException {
        convert(ori,Charset.forName("GBK"), des, Charset.forName("UTF-8"));
    }
    public void toGBK(File ori, File des) throws IOException {
        convert(ori,Charset.forName("UTF-8"), des, Charset.forName("GBK"));
    }
}
