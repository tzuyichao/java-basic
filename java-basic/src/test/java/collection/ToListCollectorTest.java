package collection;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ToListCollectorTest {
    @Test
    public void test_happy() {
        List<Dish> allDishes = Dishes.getMenu().stream().collect(new ToListCollector<>());
        assertThat(allDishes)
                .isNotNull()
                .satisfies(dishes -> {
                    assertThat(dishes.size())
                            .isEqualTo(7);
                    System.out.println(dishes);
                });

    }
}
