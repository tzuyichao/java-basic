package concurrent;

public class DemoPinnnedThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.startVirtualThread(() -> DemoPinnnedThread.causePinnnedThread(0));
        Thread thread1 = Thread.startVirtualThread(() -> DemoPinnnedThread.causePinnnedThread(1));
        Thread thread2 = Thread.startVirtualThread(() -> DemoPinnnedThread.causePinnnedThread(2));

        thread.join();
        thread1.join();
        thread2.join();
    }

    private static void causePinnnedThread(int i) {
        System.out.println("i = " + i);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
