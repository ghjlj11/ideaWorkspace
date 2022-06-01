package fileAndIO;

import java.io.*;

/**
 * @author 86187
 * 照片
 */
public class TestPicture {
    public static void main(String[] args) throws IOException {
        File file = new File("D:/my-study/ideaWorkspace/test/ak.png");
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new FileOutputStream("D:/my-study/ideaWorkspace/test/测试文件夹/aaa.png");
        long l = inputStream.transferTo(outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
