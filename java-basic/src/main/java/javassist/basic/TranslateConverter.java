package javassist.basic;

import javassist.*;

/**
 * 由 CodeConverter 处理的转换很有用，但是有局限性。例如，如果希望在调用目标方法之前或者之后调用一个监视方法，
 * 那么这个监视方法必须定义为 static void 并且必须先接受一个目标方法的类的参数，然后是与目标方法所要求的同样数量和类型的参数。
 *
 * program args: javassist.basic.Bean setA javassist.basic.BeanTest
 */
public class TranslateConverter {
    public static void main(String[] args) {
        if(args.length >= 3) {
            try {
                ConverterTranslator translator = new ConverterTranslator();
                ClassPool classPool = ClassPool.getDefault();
                CodeConverter codeConverter = new CodeConverter();

                CtMethod subjectMethod = classPool.get(args[0])
                        .getDeclaredMethod(args[1]);

                CtMethod wrapMethod = classPool.get("javassist.basic.TranslateConverter")
                        .getDeclaredMethod("reportSet");

                codeConverter.insertBeforeMethod(subjectMethod, wrapMethod);

                translator.setCodeConverter(codeConverter);

                Loader loader = new Loader();
                loader.addTranslator(classPool, translator);

                String[] pargs = new String[args.length - 3];
                System.arraycopy(args, 3, pargs, 0, pargs.length);
                loader.run(args[2], pargs);
            } catch(NotFoundException | CannotCompileException e) {
                e.printStackTrace();
            } catch(Throwable e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Usage: TranslateConverter class-name set-name main-class args...");
        }
    }

    public static void reportSet(Bean target, String value) {
        System.out.println("Call to set value " + value);
    }

    public static class ConverterTranslator implements Translator {
        private CodeConverter codeConverter;

        private void setCodeConverter(CodeConverter codeConverter) {
            this.codeConverter = codeConverter;
        }

        @Override
        public void start(ClassPool pool) throws NotFoundException, CannotCompileException {

        }

        @Override
        public void onLoad(ClassPool pool, String className) throws NotFoundException, CannotCompileException {
            CtClass ctClass = pool.get(className);
            ctClass.instrument(codeConverter);
        }
    }
}
