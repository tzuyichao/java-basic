package collection;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupingBuilderTest {
    @Test
    public void testHappy() {
        Collector<? super Dish, ?, Map<Dish.Type, List<Dish>>> dishGroupingCollector = GroupingBuilder.groupOn(Dish::getType).get();

        Map<Dish.Type, List<Dish>> result = Dishes.getMenu().stream().collect(dishGroupingCollector);
        assertThat(result)
                .isNotNull();
        System.out.println(result);
    }
}
