package create;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.impl.UnionListWriter;
import org.junit.jupiter.api.Test;

public class ArrayOfListTests {
    @Test
    void test_create_array_of_list_happy() {
        try(BufferAllocator allocator = new RootAllocator();
            ListVector listVector = ListVector.empty("listVector", allocator);
            UnionListWriter listWriter = listVector.getWriter();
        ) {
            int[] data = new int[] {1, 2, 3, 10, 20, 30, 100, 200, 300, 1000, 2000, 3000};
            int tmp_index = 0;
            for(int i=0; i<4; i++) {
                listWriter.setPosition(i);
                listWriter.startList();
                for(int j=0; j<3; j++) {
                    listWriter.writeInt(data[tmp_index]);
                    tmp_index = tmp_index + 1;
                }
                listWriter.setValueCount(3);
                listWriter.endList();
            }
            listWriter.setValueCount(4);

            System.out.println(listVector);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
