package classloader;

public class LoadSomeClasses {
    public static class SadClassLoader extends ClassLoader {
        public SadClassLoader() {
            super(SadClassLoader.class.getClassLoader());
        }

        public Class<?> findClass(String name) throws ClassNotFoundException {
            System.out.println("I am very concerned that I couldn't find the class");
            throw new ClassNotFoundException(name);
        }
    }

    public static void main(String[] args) {
        if(args.length > 0) {
            var loader = new SadClassLoader();
            for(var name : args) {
                System.out.println(name + " ::");
                try {
                    var clazz = loader.loadClass(name);
                    System.out.println(clazz);
                } catch(ClassNotFoundException x) {
                    x.printStackTrace();
                }
            }
        } else {
            System.out.println("No input");
        }
    }
}
