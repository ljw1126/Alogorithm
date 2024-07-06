import java.util.*;
import java.util.function.*;
import java.util.stream.*;

class Solution {
    public int solution(String[][] clothes) {
        return Stream.of(clothes)
            .map(arr -> arr[1]) 
            .collect(Collectors.toMap(Function.identity(), e -> 1, Integer::sum)) // 카테고리별 합계 구한다
            .values()
            .stream()
            .reduce(1, (acc, count) -> acc * (count + 1)) - 1; // 아무것도 입지 않는 경우를 빼준다
    }
}