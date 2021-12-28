package lambda.decorator;

public class CakeDecoratorLab {
    public static void main(String[] args) {
        var nutsAndCream = new CakeDecorator(
                c -> c.decorate(" with Nuts"),
                c -> c.decorate(" with Cream")
        );

        Cake cake = nutsAndCream.decorate(new Cake("Base cake"));

        System.out.println(cake.getDecorations());
    }
}
