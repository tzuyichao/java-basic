package hello;

public class User {
    private Long id;
    private String name;

    public void say(String msg) {
        System.out.println(String.format("%s saying: %s", name, msg));
    }
}
