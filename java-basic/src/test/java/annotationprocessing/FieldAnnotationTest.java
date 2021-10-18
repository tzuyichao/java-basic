package annotationprocessing;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static org.assertj.core.api.Assertions.assertThat;

public class FieldAnnotationTest {
    private Field getMemberField(String fieldName) throws NoSuchFieldException {
        Field classMemberField = ClassWithAnnotations.class.getDeclaredField(fieldName);
        return  classMemberField;
    }

    @Test
    public void whenCallingGetDeclaredAnnotation_thenOnlyRuntimeAnnotationAreAvailable() throws NoSuchFieldException {
        Field classMemberField = getMemberField("classMember");
        Annotation[] annotations = classMemberField.getDeclaredAnnotations();
        assertThat(annotations).hasSize(2);
    }

    @Test
    public void checkFieldAnnotationsWithSpectificType() throws NoSuchFieldException {
        Field classMemberField = getMemberField("classMember");
        assertThat(classMemberField.isAnnotationPresent(FirstAnnotation.class)).isTrue();
        assertThat(classMemberField.isAnnotationPresent(SecondAnnotation.class)).isTrue();
        assertThat(classMemberField.isAnnotationPresent(ThirdAnnotation.class)).isFalse();
    }

    @Test
    public void checkGetAnnotationsAndGetDeclaredAnnotations() throws NoSuchFieldException {
        Field classMemberField = getMemberField("classMember");
        Annotation[] declaredAnnotations = classMemberField.getDeclaredAnnotations();
        Annotation[] annotations = classMemberField.getAnnotations();
        assertThat(declaredAnnotations).containsExactly(annotations);
    }
}
