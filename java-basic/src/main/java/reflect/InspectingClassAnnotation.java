package reflect;

import reflect.pack.Melon;

import java.lang.annotation.Annotation;

public class InspectingClassAnnotation {
    public static void main(String[] args) {
        Class<Melon> melonClazz = Melon.class;
        for(Annotation annotation : melonClazz.getAnnotations()) {
            System.out.println(annotation);
        }
    }
}
