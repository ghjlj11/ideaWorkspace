package com.ghj.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * @author 86187
 */
public class TestThread02 implements Callable<Boolean> {
    String url;
    String name;
    public TestThread02(String url, String name){
        this.url = url;
        this.name = name;
    }
    @Override
    public Boolean call(){
        WebDownLoad web = new WebDownLoad();
        try {
            web.down(url,name);
            System.out.println("下载成功:" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThread02 t1 = new TestThread02("https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0ab8e5096ac6f08bd632e4d5a15d1792.jpg?w=632&h=340","p1.jpg");
        TestThread02 t2 = new TestThread02("https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0ab8e5096ac6f08bd632e4d5a15d1792.jpg?w=632&h=340","p2.jpg");
        TestThread02 t3 = new TestThread02("https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0ab8e5096ac6f08bd632e4d5a15d1792.jpg?w=632&h=340","p3.jpg");
        ExecutorService ser = Executors.newFixedThreadPool(3);

        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);
        ser.shutdownNow();
    }
}

class WebDownLoad{
    public void down(String url, String name) throws IOException {
        try{
            FileUtils.copyURLToFile(new URL(url),new File(name));
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("发生了异常");
        }
    }
}
