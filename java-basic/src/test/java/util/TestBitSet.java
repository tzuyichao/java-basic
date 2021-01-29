package util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.BitSet;

@Slf4j
public class TestBitSet {
    private void printLengthAndSize(BitSet bitSet) {
        log.info("length: {}", bitSet.length());
        log.info("size: {}", bitSet.size());
    }

    @Test
    public void test_length() {
        BitSet bitSet = new BitSet();
        printLengthAndSize(bitSet);
        test_bitSet_length(bitSet);
    }

    private void test_bitSet_length(BitSet bitSet) {
        bitSet.set(5);
        log.info("After set(5)");
        printLengthAndSize(bitSet);
        bitSet.set(5, false);
        log.info("After set(5, false)");
        printLengthAndSize(bitSet);
        bitSet.set(64);
        log.info("After set(64)");
        printLengthAndSize(bitSet);
    }

    @Test
    public void test_length_with_sizeIsSticky() {
        BitSet bitSet = new BitSet(64);
        printLengthAndSize(bitSet);
        test_bitSet_length(bitSet);
    }
}
