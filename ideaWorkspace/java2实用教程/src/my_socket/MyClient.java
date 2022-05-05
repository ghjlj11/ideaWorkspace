package my_socket;

import java.io.IOException;
import java.net.Socket;

/**
 * @author 86187
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("172.22.56.109", 8848);
        System.out.println(socket);
    }
}
