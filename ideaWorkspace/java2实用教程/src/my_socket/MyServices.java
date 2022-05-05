package my_socket;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/**
 * @author 86187
 */
public class MyServices {
    public static void main(String[] args) throws IOException {
        //获得一个地址
        InetAddress localHost = InetAddress.getByName("172.22.56.109");
        SocketAddress socketAddress = new InetSocketAddress(localHost, 8848);

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(socketAddress);
        System.out.println(serverSocket);

        //通信
        while (true){
            Socket accept = serverSocket.accept();
            System.out.println(accept);
        }
    }
}
