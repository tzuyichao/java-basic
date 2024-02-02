package basic;

import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.types.pojo.Schema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class SchemaTests {
    @Test
    void create_schema_happy() {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("K1", "val1");
        metadata.put("Key2", "v2");

        Field a = new Field("A",
                FieldType.nullable(new ArrowType.Int(32, true)),
                null);
        Field b = new Field("B",
                FieldType.nullable(new ArrowType.Utf8()),
                null);

        Schema schema = new Schema(asList(a, b), metadata);

        System.out.println("Schema created: " + schema);
    }
}
