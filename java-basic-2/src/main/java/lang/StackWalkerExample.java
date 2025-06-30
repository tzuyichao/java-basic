package lang;

public class StackWalkerExample {
    public static void main(String[] args) {
        new StackWalkerExample().start();
    }

    public void start() {
        methodA();
    }

    public void methodA() {
        methodB();
    }

    private void methodB() {
        printStackTrace();
    }

    public static void printStackTrace() {
        StackWalker stackWalker = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        stackWalker.forEach(frame -> {
            System.out.println("Class: " + frame.getDeclaringClass().getName());
            System.out.println("Method: " + frame.getMethodName());
            System.out.println("Line number: " + frame.getLineNumber());
            System.out.println("-----------------------");
        });
    }
}
