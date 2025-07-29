import java.util.*;

class Solution {
    // 시간이 t가 주어졌을때 심사관이 검증할 수 있는 사람수가 n명 이상인가? (최소값)
    // long 최대치는 9 * 10^18
    // 입국 심사 인원 10^9명, 심사위원 한명이 걸리는 최대 시간 10^9일때 R은 10^18이 최대가 걸림
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
            if(count >= Long.MAX_VALUE - (limit / times[i])) {
                return false;
            }
            
            count += (limit / times[i]);
        }
        
        return count >= n;
    }
}