package collection;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.maxBy;

public class PartitioningLab {
    public static void main(String[] args) {
        Map<Boolean, List<Dish>> partitionedMenu = Dishes.getMenu().stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);

        Map<Boolean, Dish> mostCaloricPartitioningByVegetarian = Dishes.getMenu().stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(mostCaloricPartitioningByVegetarian);
    }
}
