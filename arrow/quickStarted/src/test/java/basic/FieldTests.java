package basic;

import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class FieldTests {
    @Test
    void create_field_happy() {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("A", "Id card");
        metadata.put("B", "Passport");
        metadata.put("C", "Visa");

        Field document = new Field("document",
                new FieldType(true, new ArrowType.Utf8(), null, metadata),
                null);
        System.out.println("Field created: " + document + ", Metadata: " + document.getMetadata());
    }
}
