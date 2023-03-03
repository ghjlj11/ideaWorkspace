package my_socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;

/**
 * @author 86187
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
//        InetAddress localHost = InetAddress.getByName("127.0.0.1");
//        SocketAddress socketAddress = new InetSocketAddress(localHost, 8848);
//
//        ServerSocket serverSocket = new ServerSocket();
//        serverSocket.bind(socketAddress);
        Socket socket = new Socket("127.0.0.1", 8848);
        OutputStreamWriter write = new OutputStreamWriter(socket.getOutputStream());
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String s = sc.nextLine();
            write.write(s);
            write.flush();
        }
        sc.close();
        write.close();
        socket.close();
    }
}
