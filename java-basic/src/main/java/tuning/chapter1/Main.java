package tuning.chapter1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Area> buildData(int count) {
        List<Area> areas = new ArrayList<>(count);
        for(int i=0; i<count; i++) {
            areas.add(new Area(i, i));
        }
        return areas;
    }

    private static void testStringKey(int max, List<Area> areas) {
        AreaService areaService = new AreaService();
        for(int i=0; i<max; i++) {
            areaService.buildArea(areas);
        }
    }

    private static void testObjectKey(int max, List<Area> areas) {
        PreferAreaService preferAreaService = new PreferAreaService();
        for(int i=0; i<max; i++) {
            preferAreaService.buildArea(areas);
        }
    }

    public static void main(String[] args) {
        List<Area> data = buildData(20);
        int max = 100000;
        Long start = System.nanoTime();
        testStringKey(max, data);
        System.out.println("testStringKey: " + (System.nanoTime() - start));
        start = System.nanoTime();
        testObjectKey(max, data);
        System.out.println("testObjectKey: " + (System.nanoTime() - start));
    }
}
