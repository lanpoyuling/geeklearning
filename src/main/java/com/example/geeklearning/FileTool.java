package com.example.geeklearning;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName FileTool
 * @Description 文件工具类
 * @Author 10932
 * @Date 8/8/2021 9:33 PM
 **/
@Component
public class FileTool {

   protected byte []  readAllBytes() throws IOException {
       InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("Hello.xlass");
       int available = resourceAsStream.available();
       byte[] bytes = new byte[available];
       resourceAsStream.read(bytes);
       return bytes;
   }

    protected byte [] decode(byte [] sourceBytes) {
        byte[] bytes = new byte[sourceBytes.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - sourceBytes[i]);
        }
        return bytes;
    }
}
