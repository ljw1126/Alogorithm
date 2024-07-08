import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int n) {
        boolean[] isPrime = new boolean[10001];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        
        for(int i = 2; i <= 10000; i++) {
            for(int j = i; j <= 10000; j += i) {
                if(i == j) continue;
                
                isPrime[j] = false;
            }
        }
        
        int operand = 2;
        Set<Integer> resultSet = new HashSet();
        while(n > 0 && operand <= 10000) {
            if(isPrime[operand]) {
                while(n % operand == 0) {
                    resultSet.add(operand);
                    n /= operand;
                }
            }
            
            operand += 1;
        }
        
        return resultSet.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}