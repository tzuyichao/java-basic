package classloader;

public class CustomClassLoader extends ClassLoader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassFromFile(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] loadClassFromFile(String filename) {
        return null;
    }
}
