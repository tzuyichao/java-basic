package string;

import java.util.Objects;

public class Pair<T1, T2> {
    private T1 value1;
    private T2 value2;

    public Pair(T1 value1, T2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T1 getLeft() {
        return this.value1;
    }

    public T2 getRight() {
        return this.value2;
    }

    public static <T1, T2> Pair of(T1 value1, T2 value2) {
        return new Pair(value1, value2);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + value1 +
                ", right=" + value2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return value1.equals(pair.value1) && value2.equals(pair.value2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2);
    }
}
