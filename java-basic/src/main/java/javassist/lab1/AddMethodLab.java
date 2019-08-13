package javassist.lab1;

import javassist.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AddMethodLab {
    public static void main(String[] args) {
        try {
            CtClass ctClass = ClassPool.getDefault().get("javassist.lab1.Point");
            CtMethod helloMethod = CtNewMethod.make("public void hello(String msg) {\nSystem.out.println(\"Hello, World! \" + msg + \"!\");\n}", ctClass);
            ctClass.addMethod(helloMethod);

            Class pointClass = ctClass.toClass();
            Point point = (Point)pointClass.getDeclaredConstructor().newInstance();
            Method helloMeth = pointClass.getDeclaredMethod("hello", String.class);

            helloMeth.invoke(point, "testing");

            System.out.println("done.");
        } catch(NotFoundException e) {
            e.printStackTrace();
        } catch(CannotCompileException e) {
            e.printStackTrace();
        } catch(NoSuchMethodException e) {
            e.printStackTrace();
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
