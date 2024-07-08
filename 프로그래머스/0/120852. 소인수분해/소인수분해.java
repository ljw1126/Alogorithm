import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int n) {
        int operand = 2;
        Set<Integer> resultSet = new LinkedHashSet();
        while(n > 0 && operand <= 10000) {
            while(n % operand == 0) {
                resultSet.add(operand);
                n /= operand;
            }

            operand += 1;
        }
        
        return resultSet.stream().mapToInt(Integer::intValue).toArray();
    }
}