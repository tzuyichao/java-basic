package transformer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.Collections;
import java.util.Set;

import static org.objectweb.asm.Opcodes.ASM8;

public class MyClassVisitor extends ClassVisitor {
    public static final String NAME_CONSTRUCTOR = "<init>";
    public static final String NAME_MAIN = "main";
    public static final Set WhiteList = Collections.unmodifiableSet(Set.of(NAME_CONSTRUCTOR, NAME_MAIN));

    public MyClassVisitor(ClassVisitor classVisitor) {
        super(ASM8, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        if(WhiteList.contains(name)) {
            return methodVisitor;
        }
        return new MyMethodVisitor(ASM8, methodVisitor, access, name, descriptor);
    }
}
