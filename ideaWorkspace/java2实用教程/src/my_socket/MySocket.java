package my_socket;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;

/**
 * @author 86187
 */
public class MySocket {
    public static void main(String[] args) throws IOException {
        InetAddress localHost = InetAddress.getByName("172.22.56.109");
        SocketAddress socketAddress = new InetSocketAddress(localHost, 8848);
        System.out.println(localHost);

        //创建一个ServerSocket， 并且绑定地址。
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(socketAddress);
        System.out.println(serverSocket);

        //通过名字获得所有的ip
        InetAddress[] allByName = InetAddress.getAllByName("DESKTOP-K05HOAV");
        System.out.println(Arrays.toString(allByName));

        //获得本机的ip地址
        InetAddress localHost1 = InetAddress.getLocalHost();
        System.out.println(localHost1);
        serverSocket.close();
    }
}
