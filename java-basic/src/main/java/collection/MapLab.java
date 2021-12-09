package collection;

import java.util.Map;

import static java.util.Map.entry;

public class MapLab {
    public static void main(String[] args) {
        Map<String, String> favoriteMovies = Map.ofEntries(
                entry("Raphael", "Star wars"),
                entry("Christina", "Matrix"),
                entry("Olivia", "James Bond"));
        System.out.println(favoriteMovies);
        favoriteMovies.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(System.out::println);
    }
}
