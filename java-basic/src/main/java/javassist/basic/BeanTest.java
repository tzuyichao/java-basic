package javassist.basic;

public class BeanTest {
    private Bean bean;
    private BeanTest() {
        this.bean = new Bean("originA", "originB");
    }

    private void print() {
        System.out.println("Bean values are " + bean.getA() + " and " + bean.getB());
    }

    private void changeValues(String lead) {
        bean.setA(lead + "A");
        bean.setB(lead + "B");
    }

    public static void main(String[] args) {
        BeanTest beanTest = new BeanTest();
        beanTest.print();
        beanTest.changeValues("new");
        beanTest.print();
    }
}
