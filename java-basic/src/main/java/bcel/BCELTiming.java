package bcel;

import org.apache.bcel.Const;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class BCELTiming {
    private static void addWrapper(ClassGen classGen, Method method) {
        InstructionFactory instructionFactory = new InstructionFactory(classGen);
        InstructionList instructionList = new InstructionList();

        ConstantPoolGen constantPoolGen = classGen.getConstantPool();
        String className = classGen.getClassName();
        MethodGen wrapperGen = new MethodGen(method, className, constantPoolGen);
        wrapperGen.setInstructionList(instructionList);

        // rename a copy of original method
        MethodGen methodGen = new MethodGen(method, className, constantPoolGen);
        classGen.removeMethod(method);
        String newMethodName = methodGen.getName() + "$impl";
        methodGen.setName(newMethodName);
        classGen.addMethod(methodGen.getMethod());
        Type resultType = methodGen.getReturnType();

        Type[] types = methodGen.getArgumentTypes();
        int slot = methodGen.isStatic()?0:1;
        for(Type type: types) {
            slot += type.getSize();
        }

        // save current time
        instructionList.append(instructionFactory.createInvoke("java.lang.System", "currentTimeMillis", Type.LONG, Type.NO_ARGS, Const.INVOKESTATIC));
        instructionList.append(instructionFactory.createStore(Type.LONG, slot));

        // call wrapped method
        int offset = 0;
        short invoke = Const.INVOKESTATIC;
        if(!methodGen.isStatic()) {
            instructionList.append(instructionFactory.createLoad(Type.OBJECT, 0));
            offset = 1;
            invoke = Const.INVOKEVIRTUAL;
        }
        for(Type type: types) {
            instructionList.append(instructionFactory.createLoad(type, offset));
            offset += type.getSize();
        }
        instructionList.append(instructionFactory.createInvoke(className, newMethodName, resultType, types, invoke));

        // print time
        instructionList.append(instructionFactory.createFieldAccess("java.lang.System",
                "out",  new ObjectType("java.io.PrintStream"),
                Const.GETSTATIC));
        instructionList.append(InstructionConst.DUP);
        instructionList.append(InstructionConst.DUP);
        String text = "Call to method " + methodGen.getName() + " took ";
        instructionList.append(new PUSH(constantPoolGen, text));
        instructionList.append(instructionFactory.createInvoke("java.io.PrintStream",
                "print", Type.VOID, new Type[] { Type.STRING },
                Const.INVOKEVIRTUAL));
        instructionList.append(instructionFactory.createInvoke("java.lang.System",
                "currentTimeMillis", Type.LONG, Type.NO_ARGS,
                Const.INVOKESTATIC));
        instructionList.append(InstructionFactory.
                createLoad(Type.LONG, slot));
        instructionList.append(InstructionConst.LSUB);
        instructionList.append(instructionFactory.createInvoke("java.io.PrintStream",
                "print", Type.VOID, new Type[] { Type.LONG },
                Const.INVOKEVIRTUAL));
        instructionList.append(new PUSH(constantPoolGen, " ms."));
        instructionList.append(instructionFactory.createInvoke("java.io.PrintStream",
                "println", Type.VOID, new Type[] { Type.STRING },
                Const.INVOKEVIRTUAL));

        // return result
        if (resultType != Type.VOID) {
            instructionList.append(InstructionFactory.
                    createLoad(resultType, slot+2));
        }
        instructionList.append(InstructionFactory.createReturn(resultType));

        // finalized
        wrapperGen.stripAttributes(true);
        wrapperGen.setMaxStack();
        wrapperGen.setMaxLocals();
        classGen.addMethod(wrapperGen.getMethod());
        instructionList.dispose();
    }

    public static void main(String[] args) {
        if (args.length == 2 && args[0].endsWith(".class")) {
            try {
                JavaClass jclas = new ClassParser(args[0]).parse();
                ClassGen cgen = new ClassGen(jclas);
                Method[] methods = jclas.getMethods();
                int index;
                for (index = 0; index < methods.length; index++) {
                    if (methods[index].getName().equals(args[1])) {
                        break;
                    }
                }
                if (index < methods.length) {
                    addWrapper(cgen, methods[index]);
                    FileOutputStream fos = new FileOutputStream(args[0]);
                    cgen.getJavaClass().dump(fos);
                    fos.close();
                } else {
                    System.err.println("Method " + args[1] + " not found in " + args[0]);
                }
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }

        } else {
            System.out.println
                    ("Usage: BCELTiming class-file method-name");
        }
    }
}
