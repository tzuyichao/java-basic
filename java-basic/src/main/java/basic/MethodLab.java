package basic;

import java.lang.reflect.Method;

public class MethodLab {
    public static void main(String[] args) throws NoSuchMethodException {
        Class nodeClz = Node.class;
        Method getIdMeth = nodeClz.getMethod("setId", int.class);
        System.out.println(getIdMeth.toString());
    }
}
