package reflect;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InspectingPackages {
    public static List<String> fetchPackagesByPrefix(String prefix) {
        // get all packages by caller's class loader
        Package[] packages = Package.getPackages();
        return Arrays.stream(packages)
                .map(Package::getName)
                .filter(n -> n.startsWith(prefix))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz = Class.forName("java.lang.Integer");
        Package packageOfClazz = clazz.getPackage();
        System.out.println(packageOfClazz);

        String packageNameOfClazz = packageOfClazz.getName();
        System.out.println(packageNameOfClazz);

        File file = new File(".");
        Package packageOfFile = file.getClass().getPackage();
        System.out.println(packageOfFile);

        String packageNameOfFile = packageOfFile.getName();
        System.out.println(packageNameOfFile);

        List<String> packages = fetchPackagesByPrefix("java.util");
        packages.stream().forEach(System.out::println);
    }
}
