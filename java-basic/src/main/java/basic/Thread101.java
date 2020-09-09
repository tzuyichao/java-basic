package basic;

public class Thread101 {
    public static void main(String[] args) {
        System.out.println("Main enter");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        System.out.println("t1 is running");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
//        t1.setDaemon(true);
        t1.start();
        System.out.println("Main Exit");
    }
}
