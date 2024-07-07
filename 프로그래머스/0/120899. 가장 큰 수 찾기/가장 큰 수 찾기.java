import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] array) {
       return IntStream.range(0, array.length)
           .mapToObj(i -> new AbstractMap.SimpleEntry<>(i, array[i])).max(Map.Entry.comparingByValue())
           .map(entry -> new int[]{entry.getValue(), entry.getKey()}).orElseThrow();
    }
   
}