package my_socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 86187
 */
public class SocketServer {
    public static void main(String[] args) {
        try {
            //创建绑定到特定端口的服务器Socket。
            ServerSocket serverSocket = new ServerSocket(10068);
            //需要接收的客户端Socket
            Socket socket = null;
            //记录客户端数量
            int count = 0;
            System.out.println("服务器启动");
            //定义一个死循环，不停的接收客户端连接
            while (true) {
                //侦听并接受到此套接字的连接
                socket = serverSocket.accept();
                //获取客户端的连接
                InetAddress inetAddress = socket.getInetAddress();
                //自己创建的线程类
                ServerThread thread = new ServerThread(socket, inetAddress);
                //启动线程
                thread.start();
                //如果正确建立连接
                count++;
                //打印客户端数量
                System.out.println("客户端数量：" + count);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}