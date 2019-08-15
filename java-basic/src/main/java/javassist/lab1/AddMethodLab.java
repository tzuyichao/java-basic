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

            /**
             * public CtMethod(CtClass returnType, String mname,
             *                     CtClass[] parameters, CtClass declaring) {
             *         this(null, declaring);
             *         ConstPool cp = declaring.getClassFile2().getConstPool();
             *         String desc = Descriptor.ofMethod(returnType, parameters);
             *         methodInfo = new MethodInfo(cp, mname, desc);
             *         setModifiers(Modifier.PUBLIC | Modifier.ABSTRACT);
             *     }
             */
            CtMethod moveXMethod = new CtMethod(CtClass.intType, "moveX", new CtClass[] {CtClass.intType}, ctClass);
            // 拿掉method的abstract
            moveXMethod.setModifiers(moveXMethod.getModifiers() & ~Modifier.ABSTRACT);
            System.out.println("moveX() Modifier: " + moveXMethod.getModifiers());
            System.out.println("Before add method to Point Class Modifier: " + ctClass.getModifiers());
            ctClass.addMethod(moveXMethod);
            System.out.println("Before method setBody to Point Class Modifier: " + ctClass.getModifiers());
            moveXMethod.setBody("{ x += $1; System.out.println(\"current x=\" + $0.x + \" \" + javassist.CtClass#intType.getName()); return x; }");
            System.out.println("After method setBody and add method to Point Class Modifier: " + ctClass.getModifiers());
            System.out.println("moveX() Modifier: " + moveXMethod.getModifiers());
            // 預設CtMethod constructor的modifier是public abstract，在addMethod()後，class的Modifier會變成abstract所以需要重新設定。
            //ctClass.setModifiers(ctClass.getModifiers() & ~Modifier.ABSTRACT);

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
