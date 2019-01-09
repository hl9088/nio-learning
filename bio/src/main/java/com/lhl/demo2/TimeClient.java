package com.lhl.demo2;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 使用基础的InputStream OutputStream读取写入数据
 * Created by lihongli on 2019/1/8
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        // 使用基础的InputStream OutputStream
        try (Socket socket = new Socket("127.0.0.1", port);
             InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()) {
            out.write("QUERY TIME ORDER".getBytes());
            out.flush();
            System.out.println("Send order to server succeed");
            while (in.available() == 0) {
                // 会出现读取不到信息的问题 不知道为什么
                System.out.println("还没有收到信息");
            }
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            System.out.println(new String(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
