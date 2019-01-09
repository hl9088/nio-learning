package com.lhl.demo;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lihongli on 2019/1/8
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                // 端口号采用默认值
            }
        }
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            while (2 > 1) {
                socket = server.accept();
                System.out.println("client [" + socket.getRemoteSocketAddress() + "] connect");
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
