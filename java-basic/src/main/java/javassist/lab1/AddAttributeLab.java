package javassist.lab1;

import javassist.*;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ClassFile;
import javassist.bytecode.DuplicateMemberException;
import javassist.bytecode.FieldInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class AddAttributeLab {
    public static void main(String[] args) {
        try {
            ClassFile classFile = ClassPool.getDefault().get("javassist.lab1.Point").getClassFile();
            FieldInfo idFieldInfo = new FieldInfo(classFile.getConstPool(), "id", "I");
            idFieldInfo.setAccessFlags(AccessFlag.PUBLIC);
            classFile.addField(idFieldInfo);

            CtClass ctClass = ClassPool.getDefault().get("javassist.lab1.Point");
            Class c = ctClass.toClass();
            Point point = (Point)c.getDeclaredConstructor().newInstance();
            Field idField = c.getDeclaredField("id");
            System.out.println(idField.toGenericString());
            System.out.println(idField.get(point));
        } catch(NotFoundException e) {
            e.printStackTrace();
        } catch(DuplicateMemberException e) {
            e.printStackTrace();
        } catch(CannotCompileException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch(InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } catch(NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
