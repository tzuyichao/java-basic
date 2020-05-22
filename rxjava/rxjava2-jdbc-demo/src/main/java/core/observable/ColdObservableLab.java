package core.observable;

import org.davidmoten.rx.jdbc.Database;

public class ColdObservableLab {
    public static void main(String[] args) {
        Database.test()
                .select("select name from person")
                .getAs(String.class)
                .blockingForEach(System.out::println);

        Database.test()
                .select("select name, score from person")
                .getAs(String.class, Integer.class)
                .map(tuple2 -> {
                    return new Person(tuple2.value1(), tuple2.value2());
                })
                .blockingForEach(System.out::println);
    }
}
