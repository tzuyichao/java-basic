package annotation.inherited;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class MyInheritedClassTest {
    @Test
    public void testInherited() {
        {
            Annotation[] annotations = MyInheritedClass.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).anyMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(NoInheritedAnnotation.class)));
        }
        {
            Annotation[] annotations = MyInheritedClassUseInterface.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(NoInheritedAnnotation.class)));
        }
        {
            Annotation[] annotations = IInheritedInterface.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).anyMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));
            assertTrue("", Arrays.stream(annotations).anyMatch(l -> l.annotationType().equals(NoInheritedAnnotation.class)));
        }
        {
            Annotation[] annotations = IInheritedInterfaceChild.class.getAnnotations();
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(IsInheritedAnnotation.class)));
            assertTrue("", Arrays.stream(annotations).noneMatch(l -> l.annotationType().equals(NoInheritedAnnotation.class)));
        }
    }
}
