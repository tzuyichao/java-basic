package collection;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.maxBy;

public class PartitioningLab {
    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n)
                .boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    public static void main(String[] args) {
        Map<Boolean, List<Dish>> partitionedMenu = Dishes.getMenu().stream()
                .collect(partitioningBy(Dish::isVegetarian));
        System.out.println(partitionedMenu);

        Map<Boolean, Dish> mostCaloricPartitioningByVegetarian = Dishes.getMenu().stream()
                .collect(partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println(mostCaloricPartitioningByVegetarian);

        Map<Boolean, List<Integer>> primes = partitionPrimes(100);
        System.out.println(primes);
    }
}
