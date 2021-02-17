package basic;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    @Test
    public void simple1() {
        Comparator<List<Integer>> c = (o1, o2) -> {
            return o1.stream().reduce(0, (a, b) -> a+b).compareTo(o2.stream().reduce(0, (a, b) -> a+b));
        };
        PriorityQueue priorityQueue = new PriorityQueue(3, c);
        System.out.println(priorityQueue);
        priorityQueue.add(List.of(1, 2));
        priorityQueue.add(List.of(2, 2));
        System.out.println(priorityQueue);
        priorityQueue.add(List.of(0, 1));
        System.out.println(priorityQueue);
        priorityQueue.add(List.of(0, 0));
        System.out.println(priorityQueue);
    }

    @Test
    public void simple2() {
        Comparator<List<Integer>> c = (o1, o2) -> {
            return o1.stream().reduce(0, (a, b) -> a+b).compareTo(o2.stream().reduce(0, (a, b) -> a+b));
        };
        PriorityQueue priorityQueue = new PriorityQueue(3, c);
        System.out.println(priorityQueue);
        priorityQueue.add(List.of(1, 2));
        priorityQueue.add(List.of(2, 2));
        System.out.println(priorityQueue);
        priorityQueue.add(List.of(0, 1));
        System.out.println(priorityQueue);
        priorityQueue.add(List.of(0, 0));
        System.out.println(priorityQueue);
    }
}
