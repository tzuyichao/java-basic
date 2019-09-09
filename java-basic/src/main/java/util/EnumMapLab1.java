package util;

import java.util.*;

/**
 * Effective Java 3rd Edition: Item 37 part 1
 */
public class EnumMapLab1 {
    public static void main(String[] args) {
        Set<Plant> garden = new HashSet<>();
        garden.add(new Plant("Petunias", Plant.LifeCycle.ANNUAL));
        garden.add(new Plant("Calibrachoa", Plant.LifeCycle.ANNUAL));
        garden.add(new Plant("Peonies", Plant.LifeCycle.PERENNIAL));
        garden.add(new Plant("Myosotis", Plant.LifeCycle.BIENNIAL));
        garden.add(new Plant("Campanula medium", Plant.LifeCycle.BIENNIAL));
        garden.add(new Plant("Digitalis", Plant.LifeCycle.BIENNIAL));

        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
        for(Plant.LifeCycle lifeCycle : Plant.LifeCycle.values()) {
            plantsByLifeCycle.put(lifeCycle, new HashSet<>());
        }
        for(Plant plant: garden) {
            plantsByLifeCycle.get(plant.getLifeCycle()).add(plant);
        }
        System.out.println(plantsByLifeCycle);
    }
}

class Plant {
    enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }

    final String name;
    final LifeCycle lifeCycle;

    Plant(String name, LifeCycle lifeCycle) {
        this.name = name;
        this.lifeCycle = lifeCycle;
    }


    public String getName() {
        return name;
    }

    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", lifeCycle=" + lifeCycle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return Objects.equals(name, plant.name) &&
                lifeCycle == plant.lifeCycle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lifeCycle);
    }
}
