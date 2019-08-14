package javassist.lab1;

import javassist.*;

public class InsertLab1 {
    public static void main(String[] args) throws Exception {
        try {
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get("javassist.lab1.Point");

            CtMethod toStringMethod = ctClass.getDeclaredMethod("toString");
            System.out.println(toStringMethod.toString());
            toStringMethod.insertAfter("{System.out.println(\"After!!! x=\" + $0.x);}");
            toStringMethod.insertBefore("{System.out.println(\"Before!!!\");}");

            //Point point = new Point(10, 20);
            Class c = ctClass.toClass();
            Point point = (Point) c.getDeclaredConstructor().newInstance();
            point.setX(20);
            System.out.println(point.toString());
        } catch(NotFoundException e) {
            e.printStackTrace();
        } catch(CannotCompileException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
