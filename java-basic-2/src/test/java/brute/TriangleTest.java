package brute;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TriangleTest {
    @Test
    public void happy_1() {
        var solver = new Triangle();
        assertThat(solver.minimumTotal(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 7), List.of(4, 1, 8, 3))), is(equalTo(11)));
    }

    @Test
    public void happy_2() {
        var solver = new Triangle();
        assertThat(solver.minimumTotal(List.of(List.of(2), List.of(3, 4), List.of(6, 5, 1), List.of(4, 1, 8, 3))), is(equalTo(10)));
    }

    @Test
    public void happy_3() {
        var solver = new Triangle();
        assertThat(solver.minimumTotal(List.of(List.of(-10))), is(equalTo(-10)));
    }
}
