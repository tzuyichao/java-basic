package util;

import org.junit.Test;

public class ZhToSimpleTest {
    @Test
    public void test_simple() {
        String original = "生命不息，奮鬥不止。台達電子";
        System.out.println(ZhToSimple.convertToSimple(original));
    }
}
