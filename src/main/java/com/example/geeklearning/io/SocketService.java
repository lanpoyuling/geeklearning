package com.example.geeklearning.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketService {

    private SocketService() {
    }

    private static final Logger logger = LoggerFactory.getLogger(SocketService.class);

    static void service(Socket socket) {
        try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)) {
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio";
            printWriter.println("Content-Length:" + body.getBytes(StandardCharsets.UTF_8).length);
            printWriter.println();
            printWriter.write(body);
        } catch (IOException e) {
            logger.error("{}", e.getMessage(), e);
        }
    }
}
