import java.util.stream.*;

class Solution {
    public String solution(int age) {
        return String.valueOf(age)
            .chars()
            .mapToObj(c -> String.valueOf((char)(49 + c)))
            .collect(Collectors.joining());
    }
    
    private String solve(int age) {
        return Stream.of(String.valueOf(age))
            .flatMap(t -> Stream.of(t.split("")))
            .map(c -> String.valueOf((char) (c.charAt(0) + 49)))
            .collect(Collectors.joining());
    }
}