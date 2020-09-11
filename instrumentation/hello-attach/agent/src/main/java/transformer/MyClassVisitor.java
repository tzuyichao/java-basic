package transformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM8;

public class MyClassVisitor extends ClassVisitor {

    public MyClassVisitor(ClassVisitor classVisitor) {
        super(ASM8, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        System.out.println(name);
        if("hello".equals(name)) {
            System.out.println("Modify hello()");
            return new MyMethodVisitor(methodVisitor, access, name, descriptor);
        }
        return methodVisitor;
    }
}
