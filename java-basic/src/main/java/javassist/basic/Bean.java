package javassist.basic;

public class Bean {
    private String a;
    private String b;

    public Bean(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public Bean() {}

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }
}
