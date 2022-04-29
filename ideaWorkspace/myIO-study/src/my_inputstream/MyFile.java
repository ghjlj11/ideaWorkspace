package my_inputstream;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author 86187
 */
public class MyFile {
    public static void main(String[] args) throws IOException {
        String path = "D:\\ideaWorkspace\\test\\test.txt";
        File file = new File(path);
        File parentFile = file.getParentFile();
        boolean newFile = file.createNewFile();
        System.out.println(newFile);
        String[] list = parentFile.list();
        System.out.println(Arrays.toString(list));
        System.out.println(file.toString());
    }
}
