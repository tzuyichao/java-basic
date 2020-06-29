package hello.around;

public class WorkerBean {
    public void doSomeWork(int numOfTimes) {
        for(int i=0; i<numOfTimes; i++) {
            work();
        }
    }

    private void work() {
        System.out.print("");
    }
}
