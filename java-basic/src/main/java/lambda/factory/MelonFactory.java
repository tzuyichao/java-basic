package lambda.factory;

import java.util.Map;
import java.util.function.Supplier;

public class MelonFactory {
    private static final Map<String, Supplier<Fruit>> MELONS = Map.of(
            "Gac", Gac::new,
            "Hemi", Hemi::new,
            "Gantaloupe", Gantaloupe::new);

    /**
     * Factory pattern can via default constructor
     * @param clazz
     * @return
     */
    public static Fruit newInstance(Class<?> clazz) {
        Supplier<Fruit> supplier = MELONS.get(clazz.getSimpleName());
        if(supplier == null) {
            throw new IllegalArgumentException("Invalid clazz argument: " + clazz);
        }
        return supplier.get();
    }

    private static final TriFunction<String, Integer, String, Melon> MELON = Melon::new;

    /**
     * Factory pattern for three arguments constructor
     * @param name
     * @param weight
     * @param color
     * @return
     */
    public static Fruit newInstance(String name, int weight, String color) {
        return MELON.apply(name, weight, color);
    }
}
