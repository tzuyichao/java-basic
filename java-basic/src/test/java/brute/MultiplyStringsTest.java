package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiplyStringsTest {

    @Test
    public void testHappy1() {
        MultiplyStrings solver = new MultiplyStrings();
        assertThat(solver.multiply("2", "3"))
                .isEqualTo("6");
    }

    @Test
    public void testHappy2() {
        MultiplyStrings solver = new MultiplyStrings();
        assertThat(solver.multiply("123", "456"))
                .isEqualTo("56088");
    }
}
