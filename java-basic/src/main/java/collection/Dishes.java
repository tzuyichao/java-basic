package collection;

import java.util.Arrays;
import java.util.List;

public final class Dishes {
    public static List<Dish> getMenu() {
        return Arrays.asList(
                new Dish("salmon", false, 390, Dish.Type.FISH),
                new Dish("pork", false, 605, Dish.Type.MEAT),
                new Dish("beef", false, 625, Dish.Type.MEAT),
                new Dish("chicken", false, 497, Dish.Type.MEAT),
                new Dish("pizza", false, 765, Dish.Type.OTHER),
                new Dish("rice", false, 300, Dish.Type.OTHER),
                new Dish("french fries", false, 880, Dish.Type.OTHER)
        );
    }
}
