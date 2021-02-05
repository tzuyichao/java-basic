package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConvertBinaryNumberInLinkedList2IntegerTest {
    ConvertBinaryNumberInLinkedList2Integer solver;

    @Before
    public void init() {
        solver = new ConvertBinaryNumberInLinkedList2Integer();
    }

    @Test
    public void simple1() {
        assertEquals(5, solver.getDecimalValue(ListNodeHelper.make(new int[] {1, 0, 1})));
    }
}
