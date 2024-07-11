import java.util.stream.*;

class Solution {
    public int solution(int a, int d, boolean[] included) {
        return IntStream.range(0, included.length)
            .filter(i -> included[i])
            .map(i -> a + (i * d))
            .sum();
    }
    
     public int solve(int a, int d, boolean[] included) {
        int answer = included[0] ? a : 0;
        int acc = a;
        
        for(int i = 1; i < included.length; i++) {
            acc += d;
            
            if(included[i]) answer += acc;
        }
        
        return answer;
    }
    
}