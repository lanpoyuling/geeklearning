package com.example.geeklearning.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName HttpServerSingleThread
 * @Description 单线程的socket
 * @Author 10932
 * @Date 8/14/2021 9:38 AM
 **/
public class HttpServerSingleThread {
    private static Logger logger = LoggerFactory.getLogger(HttpServerSingleThread.class);

    public static void main(String[] args) {
        int k = 0;
        boolean flag = true;
        try (ServerSocket serverSocket = new ServerSocket(8801)) {
            while (flag) {
                k++;
                extracted(serverSocket);
                flag = k < Integer.MAX_VALUE;
            }
        } catch (IOException e) {
            logger.error("{}", e.getMessage(), e);
        }
    }

    private static void extracted(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        SocketService.service(socket);
    }

}
