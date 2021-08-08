package com.example.geeklearning;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = GeeklearningApplication.class)
class FileToolTest {
@Resource
private FileTool fileTool;
    private static final Logger logger = LoggerFactory.getLogger(FileToolTest.class);
    @Test
    void readAllBytes() throws IOException {
        byte[] bytes = fileTool.readAllBytes();
        for (byte b : bytes) {
            logger.info("{}",b);
        }
        byte[] decode = fileTool.decode(bytes);
        for (byte b : decode) {
            logger.info("=={}",b);
        }
    }
}
