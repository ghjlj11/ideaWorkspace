package my_socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerThread extends Thread {
    Socket socket = null;
    //接收客户端的连接
    InetAddress inetAddress = null;

    public ServerThread(Socket socket, InetAddress inetAddress) {
        this.socket = socket;
        this.inetAddress = inetAddress;
    }

    @Override
    public void run() {
        //字节输入流
        InputStream inputStream = null;
        //将一个字节流中的字节解码成字符
        InputStreamReader inputStreamReader = null;
        //为输入流添加缓冲
        BufferedReader bufferedReader = null;
        //字节输出流
        OutputStream outputStream = null;
        //将写入的字符编码成字节后写入一个字节流
        OutputStreamWriter writer = null;
        try {
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            //临时
            String info = null;

            //循环读取客户端信息
            while ((info = bufferedReader.readLine()) != null) {
                //获取客户端的ip地址及发送数据
                System.out.println("服务器端接收：" + "{'from_client':'" + socket.getInetAddress().getHostAddress() + "','data':'" + info + "'}");
            }

            socket.shutdownInput();//关闭输入流

            //响应客户端请求
            outputStream = socket.getOutputStream();
            writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            writer.write("{'to_client':'" + inetAddress.getHostAddress() + "','data':'我是服务器数据'}");
            //清空缓冲区数据
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (writer != null) {
                    writer.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}