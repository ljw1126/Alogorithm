import java.util.function.*;

class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        return ComparisonOperator.compare(n, m, ineq + eq) ? 1: 0;
    }
    
    enum ComparisonOperator {
        LESS_THEN("<!", (a, b) -> a < b),
        LESS_THEN_OR_EQUAL("<=", (a, b) -> a <= b),
        GREATER_THEN(">!", (a, b) -> a > b),
        GREATER_THEN_EQUAL(">=", (a, b) -> a >= b);
        
        private String symbol;
        private BiPredicate<Integer, Integer> predicate;
        
        ComparisonOperator(String symbol, BiPredicate<Integer, Integer> predicate) {
            this.symbol = symbol;
            this.predicate = predicate;
        }
        
        public static boolean compare(int n, int m, String op) {
            for(ComparisonOperator co : values()) {
                if(co.symbol.equals(op)) {
                    return co.test(n, m);
                }
            }
            
            return false; 
        }
        
        private boolean test(int n, int m) {
            return this.predicate.test(n, m);
        }
    }
}