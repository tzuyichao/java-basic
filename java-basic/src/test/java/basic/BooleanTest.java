package basic;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BooleanTest {
    @Test
    public void test_equals() {
        assertThat(Boolean.TRUE.equals(null))
                .isFalse();
    }

    public Boolean getResult() {
        return null;
    }

    @Test
    public void test_false() {
        assertThatThrownBy(() -> {boolean result = getResult() || true;})
                .isInstanceOf(NullPointerException.class);
    }
}
