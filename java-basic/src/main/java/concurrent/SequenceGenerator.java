package concurrent;

/**
 * Reference: https://www.baeldung.com/java-mutex
 */
public class SequenceGenerator {
    private int currentValue = 0;

    public int getNextSequence() {
        currentValue = currentValue + 1;
        return currentValue;
    }
}
