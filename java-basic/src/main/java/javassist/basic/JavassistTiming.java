package javassist.basic;

import javassist.*;

import java.io.IOException;

/**
 *
 * java -cp .:/Users/tzuyichao/local/jboss-javassist-javassist/javassist.jar:build/libs/java-basic-1.0-SNAPSHOT.jar javassist.basic.JavassistTiming javassist.basic.StringBuilder buildString
 */
public class JavassistTiming {
    public static void main(String[] args) {
        if(args.length ==2) {
            try {
                CtClass clas = ClassPool.getDefault().get(args[0]);
                if(clas == null) {
                    System.err.println("Class " + args[0] + " not found");
                } else {
                    addTiming(clas, args[1]);
                    clas.writeFile();
                    System.out.println("Added timing to method " + args[0] + "." + args[1]);
                }
            } catch(NotFoundException e) {
                e.printStackTrace();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usage: JavassistTiming class method-name");
        }
    }

    private static void addTiming(CtClass clas, String methName) throws NotFoundException, CannotCompileException {
        CtMethod oldMeth = clas.getDeclaredMethod(methName);

        String newMethName = methName + "$timingImpl";
        oldMeth.setName(newMethName);

        CtMethod newMeth = CtNewMethod.copy(oldMeth, methName, clas, null);

        String type = oldMeth.getReturnType().getName();
        java.lang.StringBuilder body = new java.lang.StringBuilder();
        // start body
        body.append("{\nlong start = System.currentTimeMillis();\n");
        // invoke new method
        if(!"void".equals(type)) {
            body.append(type + " result = ");
        }
        body.append(newMethName + "($$);\n");
        // finish body
        body.append("System.out.println(\"Call to method " + methName + " took \" +\n (System.currentTimeMillis()-start) + " + "\" ms.\");\n");
        if(!"void".equals(type)) {
            body.append("return result;\n");
        }
        body.append("}");

        newMeth.setBody(body.toString());
        clas.addMethod(newMeth);

        System.out.println("Intercept method body:");
        System.out.println(body.toString());
    }
}
