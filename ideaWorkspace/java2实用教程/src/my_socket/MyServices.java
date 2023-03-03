package my_socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author 86187
 */
public class MyServices {
    public static void main(String[] args) throws IOException {
        //获得一个地址
        InetAddress localHost = InetAddress.getByName("127.0.0.1");
        SocketAddress socketAddress = new InetSocketAddress(localHost, 8848);

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(socketAddress);
        System.out.println(serverSocket);

        Socket socket = new Socket("localhost", 8848);
        InputStream inputStream = socket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        //通信
        while (true){
            String s = reader.readLine();
            System.out.println("服务端接收数据：" + s);
        }
    }
}
