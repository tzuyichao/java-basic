package reflect;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.List;

public class PackagesLab {
    public static void main(String[] args) throws IOException {
        URL[] urls = Packages.fetchJarsUrlsFromClasspath(Path.of("C:\\local\\gradle-7.3\\lib"));
        for(URL url : urls) {
            System.out.println(url.toString());
        }
        URLClassLoader urlClassLoader = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
        List<Class<?>> classList = Packages.fetchClassesFromPackage("org.apache.groovy.nio.runtime", urlClassLoader);
        System.out.println(classList);
    }
}
