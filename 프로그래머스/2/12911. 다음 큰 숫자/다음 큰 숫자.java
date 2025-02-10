import java.util.*;

class Solution {
    public int solution(int n) {
        return find(n);
    }
    
    private int find(int n) {
        int v1 = Integer.bitCount(n);
        int result = -1;
        for(int i = n + 1; i <= 1000000; i++) {
            if(v1 == Integer.bitCount(i)) {
                result = i;
                break;
            }
        }
        
        return result;
    }
}