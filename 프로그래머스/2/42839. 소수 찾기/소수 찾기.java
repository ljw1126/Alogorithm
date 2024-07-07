import java.util.*;

class Solution {
    private static final boolean[] PRIME = new boolean[10_000_001];
    private static final Set<Integer> RESULT = new HashSet<>();

    public int solution(String numbers) {
        int answer = 0;
        
        preProcess();
        
        String[] data = numbers.split("");
        boolean[] selected = new boolean[data.length];
        rec(data, "", data.length, 0, selected);
        
        return RESULT.size();
    }
    
    private void preProcess() {
        Arrays.fill(PRIME, true);
        PRIME[0] = false;
        PRIME[1] = false;
        
        // 1과 자기 자신으로만 나눠지는 수가 소수다
        for(int i = 2; i <= 10_000_000; i++) {
            for(int j = i; j <= 10_000_000; j += i) {
                if(i == j) continue;
                if(!PRIME[j]) continue;
                
                PRIME[j] = false;
            }
        }
    }
    
    private void rec(String[] data, String num, int max, int count, boolean[] selected) {
        if(!"".equals(num) && isPrime(num)) {
            RESULT.add(Integer.parseInt(num));
        }
        
        if(max == count) {
            return;
        }
        
        for(int i = 0; i < data.length; i++) {
            if(selected[i]) continue;
            
            selected[i] = true;
            
            rec(data, num + data[i], max, count + 1, selected);
            
            selected[i] = false;
        }
        
    }
    
    private boolean isPrime(String value) {
        int v = Integer.parseInt(value);
        return PRIME[v];
    }
}