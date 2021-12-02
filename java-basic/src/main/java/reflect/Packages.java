package reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class Packages {
    private static final Logger LOGGER = LoggerFactory.getLogger(Packages.class);
    public static final String JAR_EXTENSION = ".jar";
    public static final String JAR_PREFIX = "jar:";

    public static URL[] fetchJarsUrlsFromClasspath(Path classpath) throws IOException {
        List<URL> urlsOfJars = new ArrayList<>();
        List<File> jarFiles = Files.find(
                classpath,
                Integer.MAX_VALUE,
                (path, attr) -> !attr.isDirectory() && path.toString().toLowerCase().endsWith(JAR_EXTENSION))
                .map(Path::toFile)
                .collect(Collectors.toList());
        for(File jarFile: jarFiles) {
            try {
                urlsOfJars.add(jarFile.toURI().toURL());
            } catch(MalformedURLException e) {
                LOGGER.error("Bad URL for {} {}", jarFile, e);
            }
        }
        return urlsOfJars.toArray(URL[]::new);
    }

    public static List<Class<?>> fetchClassesFromJar(URL resource, String packageName) throws IOException {
        List<Class<?>> classes = new ArrayList<>();

        String resourcePath = resource.getPath();

        String jarPath = resourcePath.replaceFirst("[.]jar[!].*", ".jar")
                .replaceFirst("file:", "")
                .replace(" ", "\\ ");
        LOGGER.info("jarPath 1: {}", jarPath);
        jarPath = URI.create(jarPath).getPath();
        LOGGER.info("jarPath 2: {}", jarPath);

        JarFile jarFile = new JarFile(jarPath);
        Enumeration<JarEntry> en = jarFile.entries();
        while(en.hasMoreElements()) {
            JarEntry jarEntry = en.nextElement();
            LOGGER.info("jarEntry: {}", jarEntry);
        }

        return classes;
    }

    public static List<Class<?>> fetchClassesFromPackage(String packageName, ClassLoader classLoader) throws IOException {
        List<Class<?>> classes = new ArrayList<>();

        String packagePath = packageName.replace(".", "/");
        URL resource = classLoader.getResource(packagePath);
        if(resource != null) {
            LOGGER.info("resource: {}", resource);
            if(resource.toString().startsWith(JAR_PREFIX)) {
                LOGGER.info("load classes from jar");
                classes.addAll(fetchClassesFromJar(resource, packageName));
            } else {
                LOGGER.info("load classes from directory");
            }
        } else {
            throw new RuntimeException("Resource not found for package:" + packageName);
        }

        return classes;
    }
}
