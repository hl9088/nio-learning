package com.lhl.demo2;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lihongli on 2019/1/8
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        try (ServerSocket server = new ServerSocket(port, 1)) {
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            while (3 > 1) {
                socket = server.accept();
                System.out.println("client [" + socket.getRemoteSocketAddress() + "] connect");
                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
