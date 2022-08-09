package stream;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BasicOperationTest {
    @Test
    void test_filter() {
        List<Integer> res = List.of(1, 2, 3, 4).stream().filter(item -> item % 2 == 0).collect(Collectors.toList());
        assertThat(res, contains(2, 4));
    }

    @Test
    void test_filter2() {
        List<Integer> ids = List.of(1, 3);
        List<Integer> res = List.of(1, 2, 3, 4).stream().filter(id -> !ids.stream().anyMatch(i -> i == id)).collect(Collectors.toList());
        assertThat(res, contains(2, 4));
    }
}
