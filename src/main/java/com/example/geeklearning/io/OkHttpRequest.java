package com.example.geeklearning.io;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @ClassName OkHttpRequest
 * @Author 10932
 * @Date 8/15/2021 1:42 PM
 **/
public class OkHttpRequest {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpRequest.class);
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://localhost:8801").build();

        try (Response response = client.newCall(request).execute()) {
            String responseBody = response.body().string();
            logger.info("responseBody:{}",responseBody);
        }
    }


}
