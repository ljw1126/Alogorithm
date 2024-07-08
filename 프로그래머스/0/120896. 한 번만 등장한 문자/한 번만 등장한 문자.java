import java.util.stream.*;
import java.util.*;
import java.util.function.*;

class Solution {
    public String solution(String s) {
        return Stream.of(s.split(""))
            .collect(Collectors.toMap(Function.identity(), e -> 1, Integer::sum))
            .entrySet()
            .stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Map.Entry::getKey)
            .sorted()
            .collect(Collectors.joining());
    }
}