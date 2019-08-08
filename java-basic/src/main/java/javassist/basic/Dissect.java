package javassist.basic;

import javassist.*;
import javassist.expr.*;

/**
 * program args: javassist.basic.BeanTest
 */
public class Dissect {
    public static void main(String[] args) {
        if(args.length >= 1) {
            try {
                Translator translator = new DissectionTranslator();
                ClassPool classPool = ClassPool.getDefault();

                Loader loader = new Loader();
                loader.addTranslator(classPool, translator);

                String[] pargs = new String[args.length - 1];
                System.arraycopy(args, 1, pargs, 0, pargs.length);

                loader.run(args[0], pargs);
            } catch(NotFoundException | CannotCompileException e) {
                e.printStackTrace();
            } catch(Throwable e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Usage: javassist.basic.Dissect main-class args...");
        }
    }

    public static class DissectionTranslator implements Translator {

        @Override
        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
        }

        @Override
        public void onLoad(ClassPool pool, String className) throws NotFoundException, CannotCompileException {
            System.out.println("Dissecting Class " + className);
            CtClass ctClass = pool.get(className);
            ctClass.instrument(new VerboseEditor());
        }
    }

    public static class VerboseEditor extends ExprEditor {
        private String from(Expr expr) {
            CtBehavior source = expr.where();
            return " in " + source.getName() + " (" + expr.getFileName() + ":" + expr.getLineNumber() + ")";
        }

        @Override
        public void edit(ConstructorCall c) throws CannotCompileException {
            System.out.println(" call constructor of " + c.getClassName() + from(c));
        }

        @Override
        public void edit(MethodCall m) throws CannotCompileException {
            System.out.println(" call to " + m.getClassName() + "#" + m.getMethodName() + from(m));
        }

        @Override
        public void edit(FieldAccess f) throws CannotCompileException {
            String mode = f.isReader()?"read":"write";
            System.out.println(" " + mode + " of " + f.getClassName() + "." + f.getFileName() + from(f));
        }

        @Override
        public void edit(NewExpr e) throws CannotCompileException {
            System.out.println(" new " + e.getClassName() + from(e));
        }
    }
}
