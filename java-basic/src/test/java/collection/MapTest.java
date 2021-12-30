package collection;

import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class MapTest {

    @Test
    public void testComputeIfAbsent() {
        final var testKey = "test";
        var map = new HashMap<String, Long>();
        map.computeIfAbsent(testKey, key -> 10l);
        assertThat(map.get(testKey))
                .isEqualTo(10);
    }

    @Test
    public void testCombine() {
        final var testKey = "test";
        var map = new HashMap<String, Long>();
        map.compute(testKey, (key, old) -> null==old?1L:old+1);
        assertThat(map.get(testKey))
                .isEqualTo(1);
        map.compute(testKey, (key, old) -> null==old?1L:old+1);
        assertThat(map.get(testKey))
                .isEqualTo(2);
    }
}
