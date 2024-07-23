import java.util.*;
import java.util.function.BiFunction;

class Solution {
    
    public int solution(int N, int number) {
        List<Set<Integer>> data = new ArrayList<>();
        for(int i = 0; i <= 8; i++) {
            data.add(new HashSet<>());
        }
        
        Set<Integer> one = data.get(1); // 한자리 
        one.add(N);
     
        for(int i = 2; i <= 8; i++) {
            Set<Integer> saved = data.get(i);
            saved.add(repeat(N, i));
            
            for(int j = 1; j < i; j++) {
                int k = i - j;
                calculate(data.get(j), data.get(k), saved);
            }
        }
        
        for(int i = 1; i <= 8; i++) {
            if(data.get(i).contains(number)) {
                return i;
            }
        }
        
        return -1;
    }
    
    private boolean isEquals(int v1, int v2) {
        return v1 == v2;
    }
    
    private int repeat(int base, int n) {
        int result = 0;
        for(int i = 0; i < n; i++) {
            result *= 10;
            result += base;
        }
        return result;
    }
    
    private enum Calculator {
        PLUS((a, b) -> a + b),
        MINUS((a, b) -> a - b),
        MULTI((a, b) -> a * b),
        DIVIDE((a, b) -> b == 0 ? 0 : a / b);
        
        private BiFunction<Integer, Integer, Integer> function;
        
        private Calculator(BiFunction<Integer, Integer, Integer> function) {
            this.function = function;
        }
        
        public int apply(int a, int b) {
            return this.function.apply(a, b);
        }
    }
    
    private void calculate(Set<Integer> a, Set<Integer> b, Set<Integer> data) {
        for(Calculator c : Calculator.values()) {
            for(int _a : a) {
                for(int _b : b) {
                    data.add(c.apply(_a, _b));
                }
            }
        }
    }
    
}