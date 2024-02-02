package basic;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.types.pojo.Schema;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static java.util.Arrays.asList;

public class VectorSchemaRootTests {
    @Test
    void create_VectorSchemaRoot_happy() {
        Field age = new Field("age",
                FieldType.nullable(new ArrowType.Int(32, true)),
                null);
        Field name = new Field("name",
                FieldType.nullable(new ArrowType.Utf8()),
                null);

        Schema schema = new Schema(asList(age, name), null);

        try(
                BufferAllocator allocator = new RootAllocator();
                VectorSchemaRoot root = VectorSchemaRoot.create(schema, allocator);
                IntVector ageVector = (IntVector) root.getVector("age");
                VarCharVector nameVector = (VarCharVector) root.getVector("name");
        ) {
            root.setRowCount(3);
            ageVector.allocateNew(3);
            ageVector.set(0, 10);
            ageVector.set(1, 20);
            ageVector.set(2, 30);
            nameVector.allocateNew(3);
            nameVector.set(0, "Dave".getBytes(StandardCharsets.UTF_8));
            nameVector.set(1, "Peter".getBytes(StandardCharsets.UTF_8));
            nameVector.set(2, "Mary".getBytes(StandardCharsets.UTF_8));
            System.out.println("VectorSchemaRoot created: \n" + root.contentToTSVString());
        }
    }
}
