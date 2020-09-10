package hello;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.io.*;
import java.nio.file.Path;

import static org.objectweb.asm.Opcodes.ASM8;

/**
 * Modify from Dive into JVM Bytecode example
 */
public class AdviceAdapterLab {
    public static void main(String[] args) throws IOException {
        Path path = Path.of("target/classes/hello/DummyAPIAdapter.class");
        InputStream inputStream = new FileInputStream(path.toFile());
        ClassReader classReader = new ClassReader(inputStream);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        ClassVisitor metricsVisitor = new MetricsVisitor(ASM8, classWriter);
        classReader.accept(metricsVisitor,  ClassReader.EXPAND_FRAMES);
        byte[] modifiedBytecode = classWriter.toByteArray();
        Path newClass = Path.of("target/classes/hello/DummyAPIAdapter.class");
        try(OutputStream outputStream = new FileOutputStream(newClass.toFile())) {
            outputStream.write(modifiedBytecode);
        }
    }

    static class MetricsVisitor extends ClassVisitor {
        private int api;

        public MetricsVisitor(int api, ClassVisitor classVisitor) {
            super(api, classVisitor);
            this.api = api;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
            if(!"createTerm".equals(name)) {
                return methodVisitor;
            }
            return new AdviceAdapter(this.api, methodVisitor, access, name, descriptor) {
                @Override
                protected void onMethodEnter() {
                    super.onMethodEnter();
                    methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    methodVisitor.visitLdcInsn("enter " + name);
                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                }

                @Override
                protected void onMethodExit(int opcode) {
                    super.onMethodExit(opcode);
                    methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    if(opcode == Opcodes.ATHROW) {
                        methodVisitor.visitLdcInsn("err exit " + name);
                    } else {
                        methodVisitor.visitLdcInsn("normal exit " + name);
                    }
                    methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                }
            };
        }
    }
}
