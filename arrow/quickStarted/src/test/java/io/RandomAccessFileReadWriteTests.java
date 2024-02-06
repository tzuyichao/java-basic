package io;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.VarCharVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.ipc.ArrowFileReader;
import org.apache.arrow.vector.ipc.ArrowFileWriter;
import org.apache.arrow.vector.ipc.SeekableReadChannel;
import org.apache.arrow.vector.ipc.message.ArrowBlock;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.util.ByteArrayReadableSeekableByteChannel;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Arrays.asList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RandomAccessFileReadWriteTests {
    public static final String RANDOM_ACCESS_FILE = "random_access_to_file.arrow";
    @Test
    @Order(1)
    void test_write_out_to_file() {
        try(BufferAllocator allocator = new RootAllocator();
        ) {
            Field name = new Field("name", FieldType.nullable(new ArrowType.Utf8()), null);
            Field age = new Field("age", FieldType.nullable(new ArrowType.Int(32, true)), null);
            Schema schemaPerson = new Schema(asList(name, age));
            try(VectorSchemaRoot vectorSchemaRoot = VectorSchemaRoot.create(schemaPerson, allocator);
            ) {
                VarCharVector nameVector = (VarCharVector) vectorSchemaRoot.getVector("name");
                nameVector.allocateNew(3);
                nameVector.set(0, "David".getBytes(StandardCharsets.UTF_8));
                nameVector.set(1, "Galdis".getBytes(StandardCharsets.UTF_8));
                nameVector.set(2, "Juan".getBytes(StandardCharsets.UTF_8));
                IntVector ageVector = (IntVector) vectorSchemaRoot.getVector("age");
                ageVector.allocateNew(3);
                ageVector.set(0, 10);
                ageVector.set(1, 20);
                ageVector.set(2, 30);
                vectorSchemaRoot.setRowCount(3);
                File file = new File(RANDOM_ACCESS_FILE);
                try (
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    ArrowFileWriter writer = new ArrowFileWriter(vectorSchemaRoot, null, fileOutputStream.getChannel());
                ) {
                    writer.start();
                    writer.writeBatch();
                    writer.end();
                    System.out.println("Record batches written: " + writer.getRecordBlocks().size() + ". Number of row written: " + vectorSchemaRoot.getRowCount());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    @Order(2)
    void test_read_in_from_file() {
        File file = new File(RANDOM_ACCESS_FILE);
        try(
            BufferAllocator allocator = new RootAllocator();
            FileInputStream fileInputStream = new FileInputStream(file);
            ArrowFileReader reader = new ArrowFileReader(fileInputStream.getChannel(), allocator);
        ) {
            System.out.println("Record batches in file: " + reader.getRecordBlocks().size());
            for(ArrowBlock arrowBlock: reader.getRecordBlocks()) {
                reader.loadRecordBatch(arrowBlock);
                VectorSchemaRoot vectorSchemaRoot = reader.getVectorSchemaRoot();
                System.out.println(vectorSchemaRoot.contentToTSVString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    void test_read_from_buffer() {
        Path path = Paths.get("./" + RANDOM_ACCESS_FILE);
        try(BufferAllocator allocator = new RootAllocator();
            ArrowFileReader reader = new ArrowFileReader(new SeekableReadChannel(new ByteArrayReadableSeekableByteChannel(Files.readAllBytes(path))), allocator);
        ) {
            System.out.println("Record batches in file: " + reader.getRecordBlocks().size());
            for(ArrowBlock arrowBlock : reader.getRecordBlocks()) {
                reader.loadRecordBatch(arrowBlock);
                VectorSchemaRoot vectorSchemaRoot = reader.getVectorSchemaRoot();
                System.out.println(vectorSchemaRoot.contentToTSVString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(4)
    void test_write_out_to_buffer() {
        try(BufferAllocator allocator = new RootAllocator();
        ) {
            Field name = new Field("name", FieldType.nullable(new ArrowType.Utf8()), null);
            Field age = new Field("age", FieldType.nullable(new ArrowType.Int(32, true)), null);
            Schema schemaPerson = new Schema(asList(name, age));
            try(VectorSchemaRoot vectorSchemaRoot = VectorSchemaRoot.create(schemaPerson, allocator);
            ) {
                VarCharVector nameVector = (VarCharVector) vectorSchemaRoot.getVector("name");
                nameVector.allocateNew(3);
                nameVector.set(0, "David".getBytes(StandardCharsets.UTF_8));
                nameVector.set(1, "Galdis".getBytes(StandardCharsets.UTF_8));
                nameVector.set(2, "Juan".getBytes(StandardCharsets.UTF_8));
                IntVector ageVector = (IntVector) vectorSchemaRoot.getVector("age");
                ageVector.allocateNew(3);
                ageVector.set(0, 10);
                ageVector.set(1, 20);
                ageVector.set(2, 30);
                vectorSchemaRoot.setRowCount(3);
                try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                     ArrowFileWriter writer = new ArrowFileWriter(vectorSchemaRoot, null, Channels.newChannel(out));
                ) {
                    writer.start();
                    writer.writeBatch();
                    writer.end();
                    System.out.println("Record batches written: " + writer.getRecordBlocks().size() + ". Number of row written: " + vectorSchemaRoot.getRowCount());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @AfterAll
    static void clean() throws IOException {
        Path randomFilePath = Paths.get(RANDOM_ACCESS_FILE);
        if(Files.exists(randomFilePath)) {
            Files.delete(randomFilePath);
        }
    }
}
