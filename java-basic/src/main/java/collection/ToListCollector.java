package collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToListCollector.class);

    @Override
    public Supplier<List<T>> supplier() {
        LOGGER.debug("supplier() called");
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        LOGGER.debug("accumulator() called");
        return (acc, item) -> {
            LOGGER.info("add item {}", item);
            acc.add(item);
        };
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        LOGGER.debug("combiner() called");
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        LOGGER.debug("finisher() called");
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        LOGGER.debug("characteristics() called");
        return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
    }
}
