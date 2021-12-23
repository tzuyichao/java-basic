package reflect.nests;

public class NestLab {
    public static void main(String[] args) {
        Class<Car> clazzCar = Car.class;
        Class<Car.Engine> clazzEngine = Car.Engine.class;

        if(clazzCar.equals(clazzEngine.getNestHost())) {
            System.out.println("Done.");
        }

        for(Class nestClz : clazzCar.getNestMembers()) {
            System.out.println(nestClz.descriptorString());
        }
    }
}
