package stream;

/**
 * https://www.baeldung.com/java-negate-predicate-method-reference
 */
public class Person {
    public static final int ADULT_AGE = 18;
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public boolean isAdult() {
        return age >= ADULT_AGE;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }
}
