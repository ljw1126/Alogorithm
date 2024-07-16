import java.util.*;
import java.util.LinkedHashSet;
import java.util.stream.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = IntStream.of(numbers)
            .mapToObj(String::valueOf)
            .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))
            .collect(Collectors.joining());
        
        if(answer.startsWith("0")) return "0";
        
        return answer;
    }
    
     private String solve(int[] numbers) {
        String[] values = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            values[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(values, (a, b) -> (b + a).compareTo(a + b));
        
        StringBuilder sb = new StringBuilder();
        for(String value : values) {
            sb.append(value);
        }
        
        return sb.toString();
    }
}