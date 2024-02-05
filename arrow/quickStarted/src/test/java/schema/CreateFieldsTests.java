package schema;

import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateFieldsTests {
    @Test
    void test_create_utf8_field() {
        Field name = new Field("name", FieldType.nullable(new ArrowType.Utf8()), null);
        System.out.println(name);
        assertThat(name)
                .isNotNull();
        assertThat(name.getName())
                .isEqualTo("name");
        assertThat(name.getFieldType())
                .isNotNull();
    }

    @Test
    void test_create_int_field() {
        Field age = new Field("age", FieldType.nullable(new ArrowType.Int(32, true)), null);
        System.out.println(age);
    }

    @Test
    void test_create_list_field() {
        FieldType intType = new FieldType(true, new ArrowType.Int(32, true), null);
        FieldType listType = new FieldType(true, new ArrowType.List(), null);
        Field childField = new Field("intCol", intType, null);
        List<Field> childFields = new ArrayList<>();
        childFields.add(childField);
        Field points = new Field("points", listType, childFields);
        System.out.println(points);

    }
}
