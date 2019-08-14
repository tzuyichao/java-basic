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

            CtMethod moveXMethod = new CtMethod(CtClass.intType, "moveX", new CtClass[] {CtClass.intType}, ctClass);
            ctClass.addMethod(moveXMethod);
            moveXMethod.setBody("{ x += $1; System.out.println(\"current x=\" + $0.x); return x; }");
            ctClass.setModifiers(ctClass.getModifiers() & ~Modifier.ABSTRACT);

            Class pointClass = ctClass.toClass();
            //Point point = (Point)pointClass.getDeclaredConstructor().newInstance();
            Point point = new Point();

            Method helloMeth = pointClass.getDeclaredMethod("hello", String.class);
            helloMeth.invoke(point, "testing");

            Method moveXMeth = pointClass.getDeclaredMethod("moveX", int.class);
            moveXMeth.invoke(point, 10);

            System.out.println("done.");
        } catch(NotFoundException e) {
            e.printStackTrace();
        } catch(CannotCompileException e) {
            e.printStackTrace();
        } catch(NoSuchMethodException e) {
            e.printStackTrace();
        } catch(IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
