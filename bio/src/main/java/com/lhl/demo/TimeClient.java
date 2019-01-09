package com.lhl.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by lihongli on 2019/1/8
 */
public class TimeClient {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 端口号采用默认值
            }
        }

        try (Socket socket = new Socket("127.0.0.1", port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            // 注意是println 否则server线程读取时 因为找不到\n会堵塞
            out.println("QUERY TIME ORDER");
            System.out.println("Send order to server succeed");
            String resp = in.readLine();
            System.out.println("resp msg : " + resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
