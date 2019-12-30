package bcel;

import net.sf.cglib.core.Constants;
import org.apache.bcel.generic.*;

import java.io.IOException;

public final class HelloBCEL {

    public static void main(String[] args) {
        ClassGen classGen = new ClassGen("HelloWorld",
                "java.lang.Object",
                "<generated>",
                Constants.ACC_PUBLIC | Constants.ACC_SUPER,
                null);
        ConstantPoolGen constantPoolGen = classGen.getConstantPool();
        InstructionList instructionList = new InstructionList();
        InstructionFactory instructionFactory = new InstructionFactory(classGen);
        MethodGen mainMethodGen = new MethodGen(Constants.ACC_PUBLIC | Constants.ACC_STATIC,
                Type.VOID,
                new Type[] {
                        new ArrayType(Type.STRING, 1)
                },
                new String[] {"argv"},
                "main",
                "HelloWorld",
                instructionList,
                constantPoolGen);
        ObjectType p_stream = new ObjectType("java.io.PrintStream");
        instructionList.append(instructionFactory.createFieldAccess("java.lang.System",
                "out",
                p_stream,
                (short) Constants.GETSTATIC));
        instructionList.append(new PUSH(constantPoolGen, "Hello, World!"));
        instructionList.append(instructionFactory.createInvoke("java.io.PrintStream",
                "println",
                Type.VOID,
                new Type[] {Type.STRING},
                (short) Constants.INVOKEVIRTUAL));

        instructionList.append(InstructionConstants.RETURN);

        mainMethodGen.setMaxStack(5);
        classGen.addMethod(mainMethodGen.getMethod());

        instructionList.dispose();
        classGen.addEmptyConstructor(Constants.ACC_PUBLIC);

        try {
            classGen.getJavaClass().dump("HelloWorld.class");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
