package hello;

import org.objectweb.asm.*;

import java.io.*;
import java.nio.file.Path;

import static org.objectweb.asm.Opcodes.ASM8;


public class CoreLab {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("target/classes/hello/User.class");
        InputStream inputStream = new FileInputStream(path.toFile());
        ClassReader classReader = new ClassReader(inputStream);
        ClassWriter classWriter = new ClassWriter(0);
        ClassVisitor classVisitor = new ClassVisitor(ASM8, classWriter) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                super.visit(version, access, "User2", signature, superName, interfaces);
            }

            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                System.out.println("field: " + name);
                return super.visitField(access, name, descriptor, signature, value);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                System.out.println("method: " + name);
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }

            @Override
            public void visitEnd() {
                super.visitEnd();
                FieldVisitor fieldVisitor = this.visitField(Opcodes.ACC_PRIVATE, "email", "Ljava/lang/String;", null, null);
                if(fieldVisitor != null) {
                    fieldVisitor.visitEnd();
                }
            }
        };
        classReader.accept(classVisitor, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
        byte[] modifiedBytecode = classWriter.toByteArray();
        Path newClass = Path.of("target/classes/hello/User2.class");
        try(OutputStream outputStream = new FileOutputStream(newClass.toFile())) {
            outputStream.write(modifiedBytecode);
        }
    }
}
