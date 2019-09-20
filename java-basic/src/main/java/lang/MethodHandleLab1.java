package lang;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleLab1 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {
        Object receiver = "a";
        MethodType methodType = MethodType.methodType(int.class);
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle methodHandle = lookup.findVirtual(receiver.getClass(), "hashCode", methodType);
        int returnValue;
        try {
            returnValue = (int) methodHandle.invoke(receiver);
            System.out.println("hashCode of " + receiver + " is " + returnValue);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
