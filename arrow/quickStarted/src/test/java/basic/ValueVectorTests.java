package basic;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.VarCharVector;
import org.junit.jupiter.api.Test;

public class ValueVectorTests {
    @Test
    void test_create_int_value_vector() {
        try (BufferAllocator allocator = new RootAllocator();
             IntVector intVector = new IntVector("fixed-size-primitive-layout", allocator)
        ) {
            intVector.allocateNew(3);
            intVector.set(0, 1);
            intVector.setNull(1);
            intVector.set(2, 2);
            System.out.println("Vector created in memory: " + intVector);
            intVector.setValueCount(3);
            System.out.println("Vector created in memory: " + intVector);
            intVector.setValueCount(6);
            System.out.println("Vector created in memory: " + intVector);
        }
    }

    @Test
    void test_VarCharVector() {
        try(BufferAllocator allocator = new RootAllocator();
            VarCharVector varCharVector = new VarCharVector("variable-size-primitive-layout", allocator)
        ) {
            varCharVector.allocateNew(5);
            varCharVector.set(0, "one".getBytes());
            varCharVector.set(1, "two".getBytes());
            varCharVector.set(2, "three".getBytes());
            varCharVector.setValueCount(3);
            System.out.println("Vector created in memory: " + varCharVector);
        }
    }
}
