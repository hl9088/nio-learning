package com.lhl.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by lihongli on 2019/1/11
 */
public class Test {
    public static void main(String[] args) throws Exception{
        User user = new User(123, "admin");
        try(ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos)){
            oos.writeObject(user);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            System.out.println("The jdk serializable length is : " + bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用bytebuffer通用二进制编码技术 不理解啥意思
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] bytes = user.getUserName().getBytes();
        buffer.putInt(bytes.length);
        buffer.put(bytes);
        buffer.putInt(user.getId());
        buffer.flip();
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        System.out.println("The byte array serializable length is " + result.length);

    }
}
