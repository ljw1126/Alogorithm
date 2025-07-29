import java.util.*;

class Solution {
    // 시간이 t가 주어졌을때 모든 사람(n)이 심사받을 수 있는가? (최소값)
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        
        long L = 1L;
        long R = (long) times[times.length - 1] * n; 
        while(L <= R) {
            long mid = (L + R) / 2;
            
            if(isPossible(mid, n, times)) {
                answer = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        
        return answer;
    }
    
    private boolean isPossible(long limit, int n, int[] times) {
        long count = 0L;
        for(int i = 0; i < times.length; i++) {
            count += (limit / times[i]);
        }
        
        return count >= n;
    }
}