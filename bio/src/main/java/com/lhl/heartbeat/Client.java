package com.lhl.heartbeat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public final class Client {

    private String address;

    private int port;

    private Socket socket = null;

    private Client() {
    }

    private Client(String address, int port) throws IOException {
        this.address = address;
        this.port = port;
        this.socket = new Socket(address, port);
    }

    public static byte[] sendCommand(String address, int port, byte[] data) {
        Client client = null;
        OutputStream outputStream = null;
        InputStream inputStream = null;
        byte[] recevie = new byte[40];
        try {
            client = new Client(address, port);
            outputStream = client.getSocket().getOutputStream();
            outputStream.write(data);
//            System.out.println("Send Data Success");
            inputStream = client.getSocket().getInputStream();
            inputStream.read(recevie);
            System.out.println("client recieve: " + new String(recevie));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                client.getSocket().close();
            } catch (IOException ioe) {
                System.out.print("IOE when closing resource");
            }
        }
        return recevie;
    }

    /**
     * 测试函数
     */
    public static void test() {
        for (int i = 0; i < 10000; i++) {
            //服务器地址和IP
            String address = "127.0.0.1";
            int port = 8080;
            //心跳包
            byte[] data = "#$*beat001".getBytes();
            String receive = new String(Client.sendCommand(address, port, data));
            System.err.println("第次发送心跳包" + receive);
            /*try {
                //每隔2分钟发送一个心跳包
//                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args){
        Server server = new Server(8080);
        server.start();

        Client.test();

    }
}
