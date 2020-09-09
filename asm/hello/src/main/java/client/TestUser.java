package client;

import hello.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestUser {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class userClz = User.class;
        Method helloMethod = userClz.getMethod("hello", String.class);
        helloMethod.invoke(userClz, "Test");
    }
}
