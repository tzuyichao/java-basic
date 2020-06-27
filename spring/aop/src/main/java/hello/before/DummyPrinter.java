package hello.before;

public class DummyPrinter implements Printer {
    @Override
    public void write(String msg) {
        System.out.println(msg);
    }
}
