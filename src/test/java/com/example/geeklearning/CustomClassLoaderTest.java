package com.example.geeklearning;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@SpringBootTest(classes = GeeklearningApplication.class)
class CustomClassLoaderTest {
    private static final Logger logger = LoggerFactory.getLogger(CustomClassLoaderTest.class);
    @Resource
    CustomClassLoader customClassLoader;

    @Test
    void findClass() throws InstantiationException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        //Class<?> hello = customClassLoader.loadClass("Hello");
        Class<?> hello = customClassLoader.findClass("Hello");
        Method[] declaredMethods = hello.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            logger.info("{}",declaredMethod);
        }
        //Constructor<?> declaredConstructor = hello.getDeclaredConstructor(); Object o = declaredConstructor.newInstance();
        Object o = hello.newInstance();
        Method method = hello.getMethod("hello");
        method.invoke(o);
    }
}
