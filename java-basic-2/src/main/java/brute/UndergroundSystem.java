package brute;

import java.util.*;

/**
 * 1396. Design Underground System
 * https://leetcode.com/problems/design-underground-system/
 *
 * Runtime: 277 ms, faster than 5.05% of Java online submissions for Design Underground System.
 * Memory Usage: 113 MB, less than 5.05% of Java online submissions for Design Underground System.
 */
public class UndergroundSystem {
    static class Record {
        int sum;
        int count;
        @Override
        public String toString() {
            return "" + sum + ":" + count;
        }
    }

    private Map<Integer, Map.Entry<String, Integer>> checkInDatabase;
    private Map<String, Record> calculateDatabase;
    public static final String TRIP_PAT = "%s_%s";

    public UndergroundSystem() {
        checkInDatabase  = new HashMap<>();
        calculateDatabase = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInDatabase.put(id, new AbstractMap.SimpleEntry<>(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        Map.Entry<String, Integer> checkIn = checkInDatabase.get(id);
        String key = String.format(TRIP_PAT, checkIn.getKey(), stationName);
        if(calculateDatabase.containsKey(key)) {
            Record record = calculateDatabase.get(key);
            record.sum += (t-checkIn.getValue());
            record.count += 1;
        } else {
            Record record = new Record();
            record.sum = t-checkIn.getValue();
            record.count = 1;
            calculateDatabase.put(key, record);
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = String.format(TRIP_PAT, startStation, endStation);
        //System.out.println(calculateDatabase);
        return ((double)calculateDatabase.get(key).sum)/calculateDatabase.get(key).count;
    }
}
