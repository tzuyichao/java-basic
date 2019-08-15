package javassist.lab1;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.AccessFlag;
import javassist.bytecode.ClassFile;
import javassist.bytecode.DuplicateMemberException;
import javassist.bytecode.FieldInfo;

public class ClassFileLab1 {
    public static void main(String[] args) {
        try {
            ClassPool classPool = ClassPool.getDefault();

            ClassFile classFile = new ClassFile(false, "test.Foo", null);
            classFile.setInterfaces(new String[]{"java.lang.Cloneable"});
            FieldInfo fieldInfo = new FieldInfo(classFile.getConstPool(), "width", "I");
            fieldInfo.setAccessFlags(AccessFlag.PUBLIC);
            classFile.addField(fieldInfo);

            classPool.makeClass(classFile);

            CtClass fooCtClass = classPool.get("test.Foo");
            System.err.println(fooCtClass);

            Class fooClass = Class.forName("test.Foo", false, classPool.getClassLoader());
            System.err.println(fooClass);
        } catch(DuplicateMemberException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
