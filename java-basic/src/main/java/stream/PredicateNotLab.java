package stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateNotLab {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person(1),
                new Person(18),
                new Person(21)
        );

        List<Person> oldNotAdult = people.stream()
                .filter(person -> !person.isAdult())
                .collect(Collectors.toList());
        System.out.println(oldNotAdult);

        List<Person> notAdult = people.stream()
                .filter(Predicate.not(Person::isAdult))
                .collect(Collectors.toList());
        System.out.println(notAdult);
    }
}
