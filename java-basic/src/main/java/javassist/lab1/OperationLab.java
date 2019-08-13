package javassist.lab1;

import javassist.ClassPool;
import javassist.NotFoundException;
import javassist.bytecode.*;

public class OperationLab {
    public static void main(String[] args) {
        try {
            ClassPool classPool = ClassPool.getDefault();
            ClassFile classFile = classPool.get("javassist.lab1.Point").getClassFile();
            MethodInfo methodInfo = classFile.getMethod("move");
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            CodeIterator codeIterator = codeAttribute.iterator();

            while(codeIterator.hasNext()) {
                try {
                    int index = codeIterator.next();
                    int op = codeIterator.byteAt(index);
                    System.out.println(Mnemonic.OPCODE[op]);
                } catch(BadBytecode e) {
                    e.printStackTrace();
                }
            }
        } catch(NotFoundException e) {
            e.printStackTrace();
        }
    }
}
