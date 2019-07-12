package javassist.basic;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

public class VerboseLoader extends URLClassLoader {
    protected VerboseLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        System.out.println("loadClass: " + name);
        return super.loadClass(name);
    }

    @Override
    protected Class findClass(String name) throws ClassNotFoundException {
        Class clas = super.findClass(name);
        System.out.println("findClass: load " + name + " from this loader");
        return clas;
    }

    public static void main(String[] args) {
        if(args.length >= 1) {
            try {
                ClassLoader base = ClassLoader.getSystemClassLoader();
                URL[] urls;
                if (base instanceof URLClassLoader) {
                    urls = ((URLClassLoader) base).getURLs();
                } else {
                    urls = new URL[]{new File(".").toURI().toURL(), new File("./build/libs/java-basic-1.0-SNAPSHOT.jar").toURI().toURL()};
                }
                System.out.println("Loading from paths: ");
                Arrays.stream(urls).forEach(url -> System.out.println("  " + url));

                VerboseLoader loader = new VerboseLoader(urls, base.getParent());

                Class clas = loader.loadClass(args[0]);
                Class[] ptypes = new Class[] {args.getClass()};

                Method main = clas.getDeclaredMethod("main", ptypes);
                String[] pargs = new String[args.length-1];
                System.arraycopy(args, 1, pargs, 0, pargs.length);

                Thread.currentThread().setContextClassLoader(loader);
                main.invoke(null, new Object[] {pargs});
            } catch(MalformedURLException e) {
                e.printStackTrace();
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
            } catch(NoSuchMethodException e) {
                e.printStackTrace();
            } catch(IllegalAccessException e) {
                e.printStackTrace();
            } catch(InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Usage: VerboseLoader main-class args");
        }
    }
}
