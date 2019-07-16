package javassist.basic;

import javassist.*;

public class JavassistRun {
    public static void main(String[] args) {
        if(args.length >= 1) {
            try {
                Translator translator = new VerboseTranslator();
                ClassPool pool = ClassPool.getDefault();
                Loader loader = new Loader();
                loader.addTranslator(pool, translator);
                String[] pargs =  new String[args.length - 1];
                System.arraycopy(args, 1, pargs, 0, pargs.length);
                loader.run(args[0], pargs);
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch(CannotCompileException e) {
                e.printStackTrace();
            } catch(Throwable e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usage: JavassistRun main-class args...");
        }
    }

    public static class VerboseTranslator implements Translator {

        @Override
        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {

        }

        @Override
        public void onLoad(ClassPool pool, String classname) throws NotFoundException, CannotCompileException {
            System.out.println("onLoad called for " + classname);
        }
    }
}
