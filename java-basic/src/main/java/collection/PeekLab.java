package collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PeekLab {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream()
                .peek(x -> System.out.println(Thread.currentThread().getName() + " from stream: " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println(Thread.currentThread().getName() + " after map: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println(Thread.currentThread().getName() + " after filter: " + x))
                .limit(3)
                .peek(x -> System.out.println(Thread.currentThread().getName() + " after limit:" + x))
                .collect(Collectors.toList());
        System.out.println("===================================");
        numbers.parallelStream()
                .peek(x -> System.out.println(Thread.currentThread().getName() + " from stream: " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println(Thread.currentThread().getName() + " after map: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println(Thread.currentThread().getName() + " after filter: " + x))
                .limit(3)
                .peek(x -> System.out.println(Thread.currentThread().getName() + " after limit:" + x))
                .collect(Collectors.toList());
    }
}
