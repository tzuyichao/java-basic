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
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        ClassVisitor classVisitor = new ClassVisitor(ASM8, classWriter) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                super.visit(version, access, name, signature, superName, interfaces);
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
                MethodVisitor helloMethodVisitor = this.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC,
                        "hello", "(Ljava/lang/String;)V", null, null);
                helloMethodVisitor.visitCode();

                // when array length >5
                //helloMethodVisitor.visitIntInsn(Opcodes.BIPUSH, 100);
                helloMethodVisitor.visitInsn(Opcodes.ICONST_4);
                // newarray int用這個
                helloMethodVisitor.visitIntInsn(Opcodes.NEWARRAY, Opcodes.T_INT);
                //helloMethodVisitor.visitInsn(Opcodes.DUP);

                helloMethodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                helloMethodVisitor.visitIntInsn(Opcodes.ALOAD, 0);
                helloMethodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                helloMethodVisitor.visitInsn(Opcodes.RETURN);
                helloMethodVisitor.visitMaxs(0, 0);
                helloMethodVisitor.visitEnd();
            }
        };
        classReader.accept(classVisitor,  0);
        byte[] modifiedBytecode = classWriter.toByteArray();
        Path newClass = Path.of("target/classes/hello/User.class");
        try(OutputStream outputStream = new FileOutputStream(newClass.toFile())) {
            outputStream.write(modifiedBytecode);
        }
    }
}
