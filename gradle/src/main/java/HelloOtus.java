import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Arrays;
import java.util.List;

public class HelloOtus {
    public static void main(String[] args) {
        Multiset<String> counts = HashMultiset.create();
        List<String> words = Arrays.asList("a", "b", "c");
        for (String word : words) {
            counts.add(word);
        }
        counts.forEach(System.out::println);
    }
}
