package fileAndIO;

import java.io.*;

/**
 * @author 86187
 */
public class FileSplitter {
    private int size;

    public FileSplitter(int size) {
        this.size = size;
    }

    public static FileSplitter getInstance(int size){
        return new FileSplitter(size);
    }

    public int split(File origin, File destination) throws IOException {
        InputStream in = new FileInputStream(origin);
        byte[] bytes = new byte[size];

        int j , i = 0;
        while ( (j = in.read(bytes,0,bytes.length) ) != -1){
            File file = new File(destination,"第" + ++i + "个文件.txt");
            file.createNewFile();
            OutputStream ou = new FileOutputStream(file);
            ou.write(bytes);
            ou.flush();
            ou.close();
        }
        in.close();
        return i;
    }
}
