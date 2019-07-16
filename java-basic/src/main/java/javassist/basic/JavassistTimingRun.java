package javassist.basic;

import javassist.*;

public class JavassistTimingRun {
    public static void main(String[] args) {
        if(args.length >= 3) {
            try {
                Translator translator = new TimingTranslator(args[0], args[1]);
                ClassPool pool = ClassPool.getDefault();
                Loader loader = new Loader();
                loader.addTranslator(pool, translator);
                String[] pargs =  new String[args.length - 3];
                System.arraycopy(args, 3, pargs, 0, pargs.length);
                loader.run(args[2], pargs);
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch(CannotCompileException e) {
                e.printStackTrace();
            } catch(Throwable e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usage: JavassistTimingRun class-name method-mname main-class args...");
        }

    }

    private static void addTiming(CtClass clas, String methodName) throws NotFoundException, CannotCompileException {
        CtMethod oldMethod = clas.getDeclaredMethod(methodName);

        String newMethodName = methodName + "$timingImpl";
        oldMethod.setName(newMethodName);

        CtMethod newMethod = CtNewMethod.copy(oldMethod, methodName, clas, null);
        String type = oldMethod.getReturnType().getName();
        java.lang.StringBuilder body = new java.lang.StringBuilder();
        // start body
        body.append("{\nlong start = System.currentTimeMillis();\n");
        // invoke new method
        if(!"void".equals(type)) {
            body.append(type + " result = ");
        }
        body.append(newMethodName + "($$);\n");
        // finish body
        body.append("System.out.println(\"Call to method " + methodName + " took \" +\n (System.currentTimeMillis()-start) + " + "\" ms.\");\n");
        if(!"void".equals(type)) {
            body.append("return result;\n");
        }
        body.append("}");

        newMethod.setBody(body.toString());
        clas.addMethod(newMethod);
    }

    public static class TimingTranslator implements Translator {
        private String className;
        private String methodName;

        public TimingTranslator(String className, String methodName) {
            this.className = className;
            this.methodName = methodName;
        }

        @Override
        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {

        }

        @Override
        public void onLoad(ClassPool pool, String classname) throws NotFoundException, CannotCompileException {
            if(classname.equals(className)) {
                CtClass clas = pool.get(className);
                addTiming(clas, methodName);
            }
        }
    }
}
