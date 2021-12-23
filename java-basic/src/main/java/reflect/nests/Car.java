package reflect.nests;

public class Car {
    private String type = "Decia";

    public class Engine {
        private String power = "80 hp";

        public void addEngine() {
            System.out.println("Add engine of " + power + " to car of type " + type);
        }
    }
}
