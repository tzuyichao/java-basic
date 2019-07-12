package javassist.basic;

import java.net.URL;
import java.net.URLClassLoader;

public class VerboseLoader extends URLClassLoader {
    protected VerboseLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
