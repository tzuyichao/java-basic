package concurrent;

public class SequenceGeneratorUsingSynchronizedMethod extends SequenceGenerator {
    @Override
    public synchronized int getNextSequence() {
        return super.getNextSequence();
    }
}
