package com.lhl.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * Created by lihongli on 2019/1/8
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()) {
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            System.out.println(new String(bytes));
            out.write("test info".getBytes());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            this.socket = null;
        }
    }
}
