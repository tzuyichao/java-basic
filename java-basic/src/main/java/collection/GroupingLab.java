package collection;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

public class GroupingLab {
    public enum CaloricLevel { DIET, NORMAL, FAT }

    public static void main(String[] args) {
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = Dishes.getMenu().stream().collect(
                groupingBy(dish -> {
                    if(dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if(dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                })
        );
        System.out.println(dishesByCaloricLevel);

        Map<Dish.Type, List<Dish>> caloricDishesByType = Dishes.getMenu().stream()
                .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
        System.out.println(caloricDishesByType);

        Map<Dish.Type, Long> typesCount = Dishes.getMenu().stream()
                .collect(groupingBy(Dish::getType, counting()));
        System.out.println(typesCount);
    }
}
