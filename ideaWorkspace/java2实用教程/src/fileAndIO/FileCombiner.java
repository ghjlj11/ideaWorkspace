package fileAndIO;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author 86187
 */
public class FileCombiner {
    private Comparator<File> comparator;

    public static FileCombiner getInstance(Comparator<File> comparator) {
        FileCombiner fileCombiner = new FileCombiner();
        fileCombiner.comparator = comparator;
        return fileCombiner;
    }

    public static FileCombiner getInstance() {
        Comparator<File> comparator = new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getPath().compareTo(o1.getPath());
            }
        };
        return getInstance(comparator);
    }

    public void combine(File path, File dest) throws IOException {
        File[] files = dest.listFiles();
        Arrays.sort(files, comparator);
        OutputStream os = new FileOutputStream(path);
        for(int i = 0 ; i < files.length ; i ++){
            InputStream is = new FileInputStream(files[i]);;
            byte[] bytes = is.readAllBytes();
            os.write(bytes);
            is.close();
        }
        os.flush();
        os.close();
    }
}
