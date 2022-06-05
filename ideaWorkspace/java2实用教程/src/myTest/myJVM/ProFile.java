package myTest.myJVM;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 86187
 */
public class ProFile {
    byte[] b = new byte[1024 * 1024];
    public static void main(String[] args) {
        List<ProFile> list = new ArrayList<>();
        int count = 0;
        try {
            while (true){
                list.add(new ProFile());
                count++;
            }
        } catch (Error e) {
            System.out.println(count);
            e.printStackTrace();
        }
    }
}
