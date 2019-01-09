package com.lhl.heartbeat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private ServerSocket server;

    public Server(int port) {
        //创建一个服务器，同时可以接收10个设备发送的数据
        try {
            server = new ServerSocket(port, 10);
            System.out.println("Socket服务器启动，开始接受数据");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            byte[] inputData = new byte[1024];
            try {
                //接收Socket数据
                socket = server.accept();
                //获取远程采集机的IP和端口号
//                String remoteAddress = socket.getInetAddress().toString();
//                int remotePort = socket.getPort();
                inputStream = socket.getInputStream();
                //获取传入的数据长度
                int length = inputStream.read(inputData);
                //创建输出流向客户端返回信息
                outputStream = socket.getOutputStream();
//                if (length == 10) {
                    //如果长度等于10，说明传入的是心跳包
                    System.out.println("server接收到心跳包，客户端信息["  + ":"  + "]" + new String(inputData));
                    outputStream.write("success".getBytes());
//                } else {
//                    System.out.println("server接收的信息有误." + new String(inputData));
//                    outputStream.write("failed".getBytes());
//                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
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
}
