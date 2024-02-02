package basic;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.ipc.ArrowFileReader;
import org.apache.arrow.vector.ipc.ArrowFileWriter;
import org.apache.arrow.vector.ipc.message.ArrowBlock;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.types.pojo.Schema;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Arrays.asList;

public class InterprocessCommunicationTests {
    @Test
    void test_to_file_happy() {
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

            File file = new File("random_access_file.arrow");
            try (
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    ArrowFileWriter writer = new ArrowFileWriter(root, null, fileOutputStream.getChannel());
            ) {
                writer.start();
                writer.writeBatch();
                writer.end();
                System.out.println("Record batches written: " + writer.getRecordBlocks().size() + ". Number of rows written: " + root.getRowCount());

            } catch (IOException e) {
                e.printStackTrace();
            }

            try (
                    FileInputStream fileInputStream = new FileInputStream(new File("random_access_file.arrow"));
                    ArrowFileReader reader = new ArrowFileReader(fileInputStream.getChannel(), allocator);
            ) {
                System.out.println("Record batches in file");
                for(ArrowBlock arrowBlock : reader.getRecordBlocks()) {
                    reader.loadRecordBatch(arrowBlock);
                    VectorSchemaRoot readRoot = reader.getVectorSchemaRoot();
                    System.out.println("VectorSchemaRoot read:\n" + readRoot);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterAll
    static void clean() throws IOException {
        Files.delete(Path.of("random_access_file.arrow"));
    }
}
