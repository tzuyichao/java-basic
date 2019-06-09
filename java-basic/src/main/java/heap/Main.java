package heap;

public class Main {
    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.add(1);
        heap.add(3);
        heap.add(6);
        heap.add(4);
        heap.add(8);
        heap.add(7);

        heap.print();

        System.out.println();

        heap.add(5);
        heap.print();

        System.out.println();

        System.out.println(heap.poll());
        heap.print();

        System.out.println();

        System.out.println(heap.poll());
        heap.print();

        System.out.println();

        System.out.println(heap.poll());
        heap.print();

        System.out.println();

        System.out.println(heap.poll());
        heap.print();

        System.out.println();

        System.out.println(heap.poll());
        heap.print();

        System.out.println();

        System.out.println(heap.poll());
        heap.print();

        System.out.println();

        System.out.println(heap.poll());
        heap.print();
    }
}
