package create;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.IntVector;
import org.apache.arrow.vector.util.TransferPair;
import org.junit.jupiter.api.Test;

public class SlicingIntVectorTests {
    @Test
    void test_slicing_happy() {
        try(BufferAllocator allocator = new RootAllocator();
            IntVector vector = new IntVector("intVector", allocator);
        ) {
            for(int i=0; i<10; i++) {
                vector.setSafe(i, i);
            }
            vector.setValueCount(10);
            System.out.println("Origin: " + vector);

            TransferPair tp = vector.getTransferPair(allocator);
            tp.splitAndTransfer(0, 5);
            try(IntVector sliced = (IntVector) tp.getTo()) {
                System.out.println("sliced: " + sliced);
                System.out.println("vector: " + vector);
            }

            tp = vector.getTransferPair(allocator);
            tp.splitAndTransfer(2, 7);
            try(IntVector sliced = (IntVector) tp.getTo()) {
                System.out.println("sliced: " + sliced);
                System.out.println("vector: " + vector);
            }
        }
    }
}
