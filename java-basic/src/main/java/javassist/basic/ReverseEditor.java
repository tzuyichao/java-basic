package javassist.basic;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;

/**
 * Program args: javassist.basic.Bean a javassist.basic.BeanTest
 */
public class ReverseEditor {
    public static void main(String[] args) {
        if(args.length >= 3) {
            try {
                Translator translator = new FieldSetTranslator(args[0], new FieldSetEditor(args[1]));
                ClassPool classPool = ClassPool.getDefault();

                Loader loader = new Loader();
                loader.addTranslator(classPool, translator);

                String[] pargs = new String[args.length - 3];
                loader.run(args[2], pargs);
            } catch(NotFoundException | CannotCompileException e) {
                e.printStackTrace();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usage: javassist.basic.ReverseEditor class-name field-name main-class args...");
        }
    }

    public static String reverse(String value) {
        String result = new java.lang.StringBuilder(value).reverse().toString();
        System.out.println("ReverseEditor#reverse to " + result);
        return result;
    }

    public static class FieldSetTranslator implements Translator {
        private String className;
        private ExprEditor exprEditor;

        private FieldSetTranslator(String className, ExprEditor exprEditor) {
            this.className = className;
            this.exprEditor = exprEditor;
        }

        @Override
        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {
        }

        @Override
        public void onLoad(ClassPool pool, String className) throws NotFoundException, CannotCompileException {
            //System.out.println(className);
            if(className.equals(this.className)) {
                CtClass ctClass = pool.get(this.className);
                ctClass.instrument(this.exprEditor);
            }
        }
    }

    public static class FieldSetEditor extends ExprEditor {
        private String fieldName;

        private FieldSetEditor(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        public void edit(FieldAccess f) throws CannotCompileException {
            if(f.getFieldName().equals(this.fieldName) && f.isWriter()) {
                StringBuffer code = new StringBuffer();
                code.append("$0.")
                        .append(f.getFieldName())
                        //.append(" = new java.lang.StringBuilder($1).reverse().toString();");
                        .append(" = javassist.basic.ReverseEditor.reverse($1);");
                f.replace(code.toString());
            }
        }
    }
}
