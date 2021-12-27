package reflect;

import reflect.pack.Melon;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

public class InspectingPackageAnnotations {
    public static void main(String[] args) {
        Class<Melon> melonClazz = Melon.class;
        Annotation[] packageAnnotations = melonClazz.getPackage().getAnnotations();
        Stream.of(packageAnnotations).forEach(annotation -> {
            System.out.println(annotation.toString());
        });
    }
}
