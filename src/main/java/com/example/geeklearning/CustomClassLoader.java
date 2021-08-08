package com.example.geeklearning;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName CustomClassLoader
 * @Description 自定义类加载器
 * @Author 10932
 * @Date 8/8/2021 8:58 PM
 **/
@Component
public class CustomClassLoader extends ClassLoader {
    @Resource
    private FileTool fileTool;


    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = fileTool.readAllBytes();
        byte[] decode = fileTool.decode(bytes);
        return defineClass(name, decode, 0, decode.length);
    }
}
