package basic;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookComparatorLab {
    public static void main(String[] args) {
        List<Book> books = Arrays.asList(
                new Book("Design Patterns", BigDecimal.valueOf(57.99)),
                new Book("Applied Clojure", BigDecimal.valueOf(27.99)),
                new Book("Refactoring", BigDecimal.valueOf(37.99))
        );

        System.out.println(books);
        Collections.sort(books, Comparator.comparing(Book::getName));
        System.out.println(books);
        Collections.sort(books, Comparator.comparing(Book::getName).reversed());
        System.out.println(books);

        Collections.sort(books, Comparator.comparing(Book::getPrice));
        System.out.println(books);
        Collections.sort(books, Comparator.comparing(Book::getPrice).reversed());
        System.out.println(books);
    }
}
