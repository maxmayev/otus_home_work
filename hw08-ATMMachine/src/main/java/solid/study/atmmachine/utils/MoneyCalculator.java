package solid.study.atmmachine.utils;

import java.util.HashMap;
import java.util.Map;

public class MoneyCalculator {
    public static int calculateSum(Map<Integer, Integer> notes) {
        return notes
                .entrySet()
                .stream()
                .mapToInt(entry -> entry.getKey() * entry.getValue())
                .sum();
    }

    public static Map<Integer, Integer> calculateNotes(int sum) {
        int notes5000 = sum / 5000;
        int notes1000 = (sum - notes5000 * 5000) / 1000;
        int notes500 = (sum - notes5000 * 5000 - notes1000 * 1000) / 500;
        int notes100 = (sum - notes5000 * 5000 - notes1000 * 1000 - notes500 * 500) / 100;

        Map<Integer, Integer> notesMap = new HashMap<>();

        notesMap.put(5000, notes5000);
        notesMap.put(1000, notes1000);
        notesMap.put(500, notes500);
        notesMap.put(100, notes100);

        return notesMap;
    }
}
