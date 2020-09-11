package app;

public class MyTest {
    public static void main(String[] args) {
        new MyTest().foo();
    }

    public void foo() {
        bar1();
        bar2();
    }

    private void bar1() {
        System.out.println("running bar1...");
    }

    private void bar2() {
        System.out.println("running bar2...");
    }
}
