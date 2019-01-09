package com.lhl.demo3;

import com.lhl.demo.TimeServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步IO
 * <p>
 * Created by lihongli on 2019/1/8
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);
            Socket socket = null;
            // 创建IO任务线程池
            TimeServerHanlderExecutePool pool = new TimeServerHanlderExecutePool(50, 1000);
            while (2 > 1) {
                socket = server.accept();
                System.out.println("client [" + socket.getRemoteSocketAddress() + "] connect");
                pool.execute(new TimeServerHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
                System.out.println("The time server close");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
