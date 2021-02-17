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
            int io1 = o1.stream().reduce(0, (a, b) -> a+b).intValue();
            int io2 = o2.stream().reduce(0, (a, b) -> a+b).intValue();
            System.out.println(io1 + ":" + io2);
            return Integer.compare(io1, io2);
        };
        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue(3, c);
        System.out.println(priorityQueue);
        priorityQueue.add(List.of(1, 3));
        priorityQueue.add(List.of(1, 5));
        System.out.println(priorityQueue);
        priorityQueue.add(List.of(1, 7));
        System.out.println(priorityQueue);
        priorityQueue.add(List.of(1, 9));
        System.out.println(priorityQueue);
        priorityQueue.add(List.of(2, 3));
        System.out.println(priorityQueue);;
        priorityQueue.add(List.of(2, 5));
        System.out.println(priorityQueue);;
    }

    @Test
    public void simple3() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(4);
        System.out.println(priorityQueue);
        priorityQueue.add(6);
        System.out.println(priorityQueue);
        priorityQueue.add(8);
        System.out.println(priorityQueue);
        priorityQueue.add(10);
        System.out.println(priorityQueue);
        priorityQueue.add(5);
        System.out.println(priorityQueue);
        priorityQueue.add(7);
        System.out.println(priorityQueue);
    }

    @Test
    public void simple4() {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        boolean inserted = priorityQueue.add(4);
        System.out.println(priorityQueue);
        inserted = priorityQueue.add(6);
        System.out.println(priorityQueue);
        if(inserted) {
            priorityQueue.stream().sorted();
            System.out.println(priorityQueue);
        }

        inserted = priorityQueue.add(8);
        System.out.println(priorityQueue);
        if(inserted) {
            priorityQueue.stream().sorted();
            System.out.println(priorityQueue);
        }

        inserted = priorityQueue.add(10);
        System.out.println(priorityQueue);
        if(inserted) {
            priorityQueue.stream().sorted();
            System.out.println(priorityQueue);
        }

        inserted = priorityQueue.add(5);
        System.out.println(priorityQueue);
        if(inserted) {
            priorityQueue.stream().sorted();
            System.out.println(priorityQueue);
        }

        inserted = priorityQueue.add(7);
        System.out.println(priorityQueue);
        if(inserted) {
            priorityQueue.stream().sorted();
            System.out.println(priorityQueue);
        }
    }
}
