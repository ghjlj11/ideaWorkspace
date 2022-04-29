package com.ghj.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author 86187
 */
public class TestThread02 extends Thread{
    String url;
    String name;
    public TestThread02(String url, String name){
        this.url = url;
        this.name = name;
    }
    @Override
    public void run(){
        WebDownLoad web = new WebDownLoad();
        try {
            web.down(url,name);
            System.out.println("下载成功:" + name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestThread02 t1 = new TestThread02("https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0ab8e5096ac6f08bd632e4d5a15d1792.jpg?w=632&h=340","p1.jpg");
        TestThread02 t2 = new TestThread02("https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0ab8e5096ac6f08bd632e4d5a15d1792.jpg?w=632&h=340","p2.jpg");
        TestThread02 t3 = new TestThread02("https://cdn.cnbj1.fds.api.mi-img.com/mi-mall/0ab8e5096ac6f08bd632e4d5a15d1792.jpg?w=632&h=340","p3.jpg");
        t1.start();
        t2.start();
        t3.start();
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
