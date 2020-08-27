package bcel;

import net.sf.cglib.core.Constants;
import org.apache.bcel.generic.*;

import java.io.IOException;

/**
 * 建立一個Class裡面有兩個method name test1，這兩個method signature都一樣只有return type不一樣
 * Java Compiler不允許但是JVM本身支援的情境
 */
public final class Test1BCEL {
    public static void main(String[] args) {
        final String ClassName = "HelloJVM";
        ClassGen classGen = new ClassGen(ClassName,
                "java.lang.Object",
                "<generated>",
                Constants.ACC_PUBLIC | Constants.ACC_SUPER,
                null);
        ConstantPoolGen constantPoolGen = classGen.getConstantPool();
        System.out.println("Constant Pool Default Size:" + constantPoolGen.getSize());
        InstructionList instructionList = new InstructionList();
        InstructionFactory instructionFactory = new InstructionFactory(classGen);

        MethodGen test1MethodGen = new MethodGen(Constants.ACC_PUBLIC,
                Type.INT,
                Type.NO_ARGS,
                null,
                "test1",
                ClassName,
                instructionList,
                constantPoolGen);
        instructionList.append(new PUSH(constantPoolGen, 5));
        instructionList.append(InstructionFactory.createReturn(Type.INT));

        test1MethodGen.setMaxLocals();
        test1MethodGen.setMaxStack();
        classGen.addMethod(test1MethodGen.getMethod());
        instructionList.dispose();

        MethodGen test2MethodGen = new MethodGen(Constants.ACC_PUBLIC,
                Type.DOUBLE,
                Type.NO_ARGS,
                null,
                "test1",
                ClassName,
                instructionList,
                constantPoolGen);
        instructionList.append(new PUSH(constantPoolGen, 10.112));
        instructionList.append(InstructionFactory.createReturn(Type.DOUBLE));

        test2MethodGen.setMaxLocals();
        test2MethodGen.setMaxStack();
        classGen.addMethod(test2MethodGen.getMethod());
        instructionList.dispose();

        MethodGen mainMethodGen = new MethodGen(Constants.ACC_PUBLIC | Constants.ACC_STATIC,
                Type.VOID,
                new Type[] {
                        new ArrayType(Type.STRING, 1)
                },
                new String[] {"argv"},
                "main",
                ClassName,
                instructionList,
                constantPoolGen);
        ObjectType p_stream = new ObjectType("java.io.PrintStream");

        instructionList.append(instructionFactory.createNew(ClassName));
        instructionList.append(InstructionFactory.createDup(1));
        instructionList.append(instructionFactory.createInvoke(ClassName,
                "<init>",
                Type.VOID,
                new Type[] {},
                (short) Constants.INVOKESPECIAL));
        instructionList.append(InstructionFactory.createStore(Type.OBJECT, 1));
        instructionList.append(instructionFactory.createFieldAccess("java.lang.System",
                "out",
                p_stream,
                (short) Constants.GETSTATIC));
        instructionList.append(InstructionFactory.createLoad(Type.OBJECT, 1));
        instructionList.append(instructionFactory.createInvoke(ClassName,
                "test1",
                Type.INT,
                Type.NO_ARGS,
                (short) Constants.INVOKEVIRTUAL));
        instructionList.append(instructionFactory.createInvoke("java.io.PrintStream",
                "println",
                Type.VOID,
                new Type[] {Type.INT},
                (short) Constants.INVOKEVIRTUAL));
        instructionList.append(instructionFactory.createFieldAccess("java.lang.System",
                "out",
                p_stream,
                (short) Constants.GETSTATIC));
        instructionList.append(InstructionFactory.createLoad(Type.OBJECT, 1));
        instructionList.append(instructionFactory.createInvoke(ClassName,
                "test1",
                Type.DOUBLE,
                Type.NO_ARGS,
                (short) Constants.INVOKEVIRTUAL));
        instructionList.append(instructionFactory.createInvoke("java.io.PrintStream",
                "println",
                Type.VOID,
                new Type[] {Type.DOUBLE},
                (short) Constants.INVOKEVIRTUAL));

        instructionList.append(InstructionFactory.createReturn(Type.VOID));

        mainMethodGen.setMaxStack();
        mainMethodGen.setMaxLocals();
        classGen.addMethod(mainMethodGen.getMethod());

        instructionList.dispose();

        classGen.addEmptyConstructor(Constants.ACC_PUBLIC);

        try {
            System.out.println(constantPoolGen.getSize());
            classGen.setMajor(53);
            classGen.setMinor(0);
            classGen.getJavaClass().dump("HelloJVM.class");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
